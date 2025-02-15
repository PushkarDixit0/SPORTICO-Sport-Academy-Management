package com.sportico.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportico.DAO.TournamentDao;
import com.sportico.DAO.TournamentEnrollmentDao;
import com.sportico.DAO.UsersDao;
import com.sportico.DTO.TournamentDTO;
import com.sportico.DTO.TournamentEnrollmentDTO;
import com.sportico.pojos.PaymentStatus;
import com.sportico.pojos.Tournament;
import com.sportico.pojos.TournamentEnrollment;
import com.sportico.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TournamentEnrollmentServiceImpl implements TournamentEnrollmentService{

	@Autowired
	public TournamentEnrollmentDao tournamentEnrollmentDao;
	
	@Autowired
	public TournamentDao tournamentDao;
	
	@Autowired
	public UsersDao userDao;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Override
	public String enrollUserInTournament(Long userId, Long tournamentId) {
       User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tournament tournament = tournamentDao.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        tournament.setSlots(tournament.getSlots()-1);
        
        boolean isAlreadyEnrolled = tournamentEnrollmentDao.existsByUserIdAndTournamentId(userId, tournamentId);
        if (isAlreadyEnrolled) {
            return "User is already enrolled in this tournament";
        }

        TournamentEnrollment enrollment = new TournamentEnrollment();
        enrollment.setUser(user);
        enrollment.setTournament(tournament);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setCreatedOn(LocalDate.now());
        enrollment.setUpdatedOn(LocalDateTime.now());

        enrollment.setStatus(PaymentStatus.PAYMENT_PENDING); 

        tournamentEnrollmentDao.save(enrollment);

        return "User enrolled successfully!";
    }

	
	@Override
	 public List<TournamentEnrollmentDTO> getAllEnrollments() {
	        List<TournamentEnrollment> enrollments = tournamentEnrollmentDao.findAll();
	        return enrollments.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    
	 private TournamentEnrollmentDTO convertToDTO(TournamentEnrollment enrollment) {
		    TournamentEnrollmentDTO dto = new TournamentEnrollmentDTO();
		    dto.setId(enrollment.getId());

		    // Get User details from enrollment
		    User user = enrollment.getUser();
		    dto.setUserId(user.getId());
		    dto.setUserName(user.getFname()+" "+user.getLname());

		    // Get Tournament details from enrollment
		    Tournament tournament = enrollment.getTournament();
		    dto.setTournamentId(tournament.getId());
		    dto.setTournamentName(tournament.getTournamentName()); 

		    dto.setEnrollmentDate(enrollment.getEnrollmentDate());
		    dto.setStatus(enrollment.getStatus());

		    return dto;
		}
	 
	 @Override
	public List<TournamentDTO> getEnrolledTournament(Long userId) {
		 List<TournamentEnrollment> enrollt = tournamentEnrollmentDao.findByUserId(userId);
		 
		 if (enrollt.isEmpty()) {
		        return Collections.emptyList();
		    }
		 
		 return enrollt.stream()
				 .map(e -> {
				TournamentDTO dto =	 modelMapper.map(e.getTournament(), TournamentDTO.class);
				dto.setTournamentId(e.getId());
				dto.setSportName(e.getTournament().getSport().getName());
				return dto;
				 })
				 .collect(Collectors.toList());
	 }

}
