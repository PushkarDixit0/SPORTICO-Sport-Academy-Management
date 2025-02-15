package com.sportico.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sportico.DAO.SportsDao;
import com.sportico.DAO.TournamentDao;
import com.sportico.DAO.TournamentEnrollmentDao;
import com.sportico.DTO.EnrolledUsersDTO;
import com.sportico.DTO.PostUserdto;
import com.sportico.DTO.TournamentDTO;
import com.sportico.DTO.TournamentEnrollmentDTO;
import com.sportico.pojos.Sport;
import com.sportico.pojos.Tournament;
import com.sportico.pojos.TournamentEnrollment;
import com.sportico.pojos.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService{
	@Autowired
	public TournamentDao tournamentDao;
	
	@Autowired
	public SportsDao sportsDao;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Autowired
	public TournamentEnrollmentDao tournamentEnrollmentDao;
	
	@Override 
	public List<TournamentDTO> getAllTournaments() {
	    List<Tournament> tournaments = tournamentDao.findAll();
	    
	    return tournaments.stream()
	            .map(tournament -> {
	             TournamentDTO tdto = 	modelMapper.map(tournament, TournamentDTO.class);
	            	if(tournament.getSport()!=null)
	            		tdto.setSportName(tournament.getSport().getName());
	            tdto.setTournamentId(tournament.getId());	
	            return tdto;
	            })
	            .collect(Collectors.toList());
	}

	
	@Override
	public TournamentDTO addTournament(TournamentDTO t) {
		Sport sport = sportsDao.findByName(t.getSportName()).orElseThrow();
		 
		Tournament tournament = modelMapper.map(t, Tournament.class);
		
		tournament.setSport(sport);
		
		 Tournament savedTournament = tournamentDao.save(tournament);
		 
		 return modelMapper.map(savedTournament, TournamentDTO.class);
	}

	@Override
	public TournamentDTO updateTournament(Long id, TournamentDTO t) {
			Tournament tournament =  tournamentDao.findById(id).orElseThrow();
			
			Sport sport = sportsDao.findByName(t.getSportName()).orElseThrow();

			        modelMapper.map(t, tournament);

				    tournament.setSport(sport);

				    Tournament savedTournament = tournamentDao.save(tournament);

				    TournamentDTO responseDTO = modelMapper.map(savedTournament, TournamentDTO.class);
				    responseDTO.setSportName(savedTournament.getSport().getName()); 
				    return responseDTO;
	}

	@Override
	public String deleteTournament(Long id) {
		if(tournamentDao.existsById(id)) {
			 tournamentDao.deleteById(id);
			return "Tournament deleted";
		}
		else
			throw new EntityNotFoundException("Tournament not found!!");
		
	}
	
	@Override
	public TournamentDTO getTournamentById(Long id) {
	    Tournament tournament = tournamentDao.findById(id)
	            .orElseThrow();

	    TournamentDTO responseDTO = modelMapper.map(tournament, TournamentDTO.class);
	    responseDTO.setSportName(tournament.getSport().getName()); 
	    return responseDTO;
	}
	

    @Scheduled( cron = "0 0 0 * * ?")  // Runs at midnight every day
    public void deleteExpiredTournaments() {
        LocalDate today = LocalDate.now();
        List<Tournament> expiredTournaments = tournamentDao.findByTournamentDateBefore(today);

        if (!expiredTournaments.isEmpty()) {
            tournamentDao.deleteAll(expiredTournaments);
            System.out.println("Deleted " + expiredTournaments.size() + " expired tournaments.");
        }
    }
    
    @Override
    public List<EnrolledUsersDTO> getEnrolledUsersByTournament(Long tournamentId) {
        List<TournamentEnrollment> enrollments = tournamentEnrollmentDao.findByTournamentId(tournamentId);
        
        return enrollments.stream().map(enrollment -> {
            EnrolledUsersDTO dto = new EnrolledUsersDTO();
            User user = enrollment.getUser();
            
            dto.setName(user.getFname()+" "+user.getLname());
            dto.setEmail(user.getEmail());
            dto.setMobNo(user.getMobNo());
          
            
            return dto;
        }).collect(Collectors.toList());
    }

}
