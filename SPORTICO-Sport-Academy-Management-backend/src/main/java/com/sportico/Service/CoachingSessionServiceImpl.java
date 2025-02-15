package com.sportico.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportico.DAO.CoachingSessionDao;
import com.sportico.DAO.SportsDao;
import com.sportico.DAO.UsersDao;
import com.sportico.DTO.CoachingSessiondto;
import com.sportico.DTO.CoachingSessiondtoPost;
import com.sportico.pojos.CoachingSession;
import com.sportico.pojos.Sport;
import com.sportico.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CoachingSessionServiceImpl implements CoachingSessionService {

	@Autowired
	public CoachingSessionDao coachingSessionDao;

	@Autowired
	public SportsDao sportsDao; 
	@Autowired
	public UsersDao userDao;
	
	@Autowired
	public ModelMapper mapper;

	@Override
	public List<CoachingSessiondto> getallSession() {
		List<CoachingSession> allCoachingSession = coachingSessionDao.findAll();
		
		
		
	    mapper.addConverter(new Converter<Sport, Long>() {
	        public Long convert(MappingContext<Sport, Long> context) {
	            return context.getSource() != null ? context.getSource().getId() : null; // Mapping to only the ID
	        }
	    });
		
	    mapper.addConverter(new Converter<User, Long>() {
            public Long convert(MappingContext<User, Long> context) {
                return context.getSource() != null ? context.getSource().getId() : null;
            }
        });
		
		
		return allCoachingSession.stream().map(session -> mapper.map(session, CoachingSessiondto.class))
				.collect(Collectors.toList());
	}
//		return allCoachingSession.stream()
//			    .map(session -> {
//			    	System.out.println(session);
//			         CoachingSessiondto dto = mapper.map(session, CoachingSessiondto.class);
//			         
//			        Sports sport=sportsDao.findById(session.getId()).orElse(null);
//			    	User coach=usersDao.findById(session.getId()).orElse(null);
//			        dto.setSportID(sport.getId());
//			        dto.setCoachID(coach.getId());
//			        
//			       
//
//			        return dto;			    	
//			    })
//			    .collect(Collectors.toList());}



	
	
	
	@Override
	public CoachingSession savesession(CoachingSessiondtoPost entity) {
		if (entity != null) {

			CoachingSession session = mapper.map(entity, CoachingSession.class);
			
			if (session.getSession_Branch()!= null) {
				
			    String timeSlot = session.getSession_Branch().getTimeSlotByBranch();
			    session.setSession_TimeSlot(timeSlot);
			    
			    System.out.println("Time Slot: " + timeSlot+"  "+session.getSession_Branch());
			}
			else{
			    System.out.println("Session Branch is null."+"  "+session.getSession_Branch());
			}
			
			Sport sport=sportsDao.findById(entity.getSportID()).orElseThrow();
				
			User coach=userDao.findById(entity.getCoachID()).orElseThrow();
			
			session.setSportID(sport);
			session.setCoachID(coach);
			
			CoachingSession coachingSession =coachingSessionDao.save(session);

			return coachingSession;

		} else {
			return null;
		}
	}
	
	
	

	@Override
	public List<CoachingSessiondto> getallSessionCID(Long cid) {
		User coach = userDao.findById(cid).orElse(null);
		List<CoachingSession> allCoachingSession = coachingSessionDao.findSessionsByCoachId(coach);
		
		
	    mapper.addConverter(new Converter<Sport, Long>() {
	        public Long convert(MappingContext<Sport, Long> context) {
	            return context.getSource() != null ? context.getSource().getId() : null; // Mapping to only the ID
	        }
	    });
		
	    mapper.addConverter(new Converter<User, Long>() {
            public Long convert(MappingContext<User, Long> context) {
                return context.getSource() != null ? context.getSource().getId() : null;
            }
        });
	    
	    
		return allCoachingSession.stream().map(session -> mapper.map(session, CoachingSessiondto.class))
				.collect(Collectors.toList());
	}





	@Override
	public List<CoachingSessiondto> getallSessionSID(Long sid) {
		Sport sport = sportsDao.findById(sid).orElse(null);
		List<CoachingSession> allCoachingSession = coachingSessionDao.findSessionsBySportId(sport);
		
		
	    mapper.addConverter(new Converter<Sport, Long>() {
	        public Long convert(MappingContext<Sport, Long> context) {
	            return context.getSource() != null ? context.getSource().getId() : null; // Mapping to only the ID
	        }
	    });
		
	    mapper.addConverter(new Converter<User, Long>() {
            public Long convert(MappingContext<User, Long> context) {
                return context.getSource() != null ? context.getSource().getId() : null;
            }
        });
	    
	    
		return allCoachingSession.stream().map(session -> mapper.map(session, CoachingSessiondto.class))
				.collect(Collectors.toList());
	}

	
	
	
	
	
	
	
	
	
	

	
}
