package com.sportico.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportico.DAO.CoachingEnrollmentDAO;
import com.sportico.DAO.CoachingSessionDao;
import com.sportico.DAO.SportsDao;
import com.sportico.DAO.UsersDao;
import com.sportico.DTO.CoachingEnrollmentdto;
import com.sportico.pojos.CoachingEnrollment;
import com.sportico.pojos.CoachingSession;
import com.sportico.pojos.PaymentStatus;
import com.sportico.pojos.SessionBatch;
import com.sportico.pojos.Sport;
import com.sportico.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CoachingEnrollmentServiceImpl implements CoachingEnrollmentService{

	@Autowired
	public CoachingEnrollmentDAO coachingEnrollmentDAO;
	
	@Autowired
	public ModelMapper mapper;
	
	@Autowired
	public SportsDao sportsDao; 
	
	@Autowired
	public UsersDao userDao;
	
	@Autowired
	public CoachingSessionDao coachingSessionDao;
	
	@Override
	public List<CoachingEnrollmentdto> getallEnrollment() {
		
		List<CoachingEnrollment> allCoachingEnroll = coachingEnrollmentDAO.findAll();
		
		
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
	    mapper.addConverter(new Converter<CoachingSession, Long>() {
            public Long convert(MappingContext<CoachingSession, Long> context) {
                return context.getSource() != null ? context.getSource().getId() : null;
            }
        });
		return allCoachingEnroll.stream().map(session -> mapper.map(session, CoachingEnrollmentdto.class))
				.collect(Collectors.toList());

	}

	
	@Override
	public List<CoachingEnrollmentdto> getallEnrollmentById(Long userId) {
		List<CoachingEnrollment> allCoachingEnroll = coachingEnrollmentDAO.findAll();
		
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
	    mapper.addConverter(new Converter<CoachingSession, Long>() {
            public Long convert(MappingContext<CoachingSession, Long> context) {
                return context.getSource() != null ? context.getSource().getId() : null;
            }
        });
	    
	    return allCoachingEnroll.stream()
	            .filter(session -> session.getUserID().getId().equals(userId)) // Filter only for matching userId
	            .map(session -> mapper.map(session, CoachingEnrollmentdto.class))
	            .collect(Collectors.toList());

	}
	
	
	
	
	@Override
	public String saveCoachinEnroll(CoachingEnrollmentdto coachingEnrollmentdto) {
				
		if(coachingEnrollmentdto!=null) {
			CoachingEnrollment coachingEnrollment =mapper.map(coachingEnrollmentdto, CoachingEnrollment.class);
			
//			coachingEnrollment.setPaymentType(coachingEnrollmentdto.getPaymentType());
			Sport sport=sportsDao.findById(coachingEnrollmentdto.getSportId()).orElseThrow();		
			User user=userDao.findById(coachingEnrollmentdto.getUserId()).orElseThrow();		
			CoachingSession coachingSession=coachingSessionDao.findById(coachingEnrollmentdto.getCoachingSessionId()).orElse(null);
			
			coachingEnrollment.setSportID(sport);
			coachingEnrollment.setUserID(user);
			coachingEnrollment.setCoachingSessionID(coachingSession);
			
			coachingEnrollmentDAO.save(coachingEnrollment);
			
			return "Save Scessfully!!!";
		}
		return "Invalid input ";
	}

	@Override
	public String deleteEnrollment(Long enrollId) {
		
		if(coachingEnrollmentDAO.existsById(enrollId)) {
			coachingEnrollmentDAO.deleteById(enrollId);
			return "Delete successfully";
		}
		
		return "Deletetion Fails";
	}
	
	

	@Override
	public String updateEnrollment(Long enrollId,SessionBatch Session) {
		
		CoachingEnrollment enrollel= coachingEnrollmentDAO.findById(enrollId).orElse(null);
		
		if(enrollel==null) {
			return "Enroll not found";
		}
		Sport sport=sportsDao.findById(enrollel.getSportID().getId()).orElse(null);
		
		
		CoachingSession coachingSession=coachingSessionDao.findBySessionBatchAndSportId(Session, sport);
		
		if(coachingSession!=null) {
		
		enrollel.setCoachingSessionID(coachingSession);
		
		
		coachingEnrollmentDAO.save(enrollel);
		
		return "Successfully Session Batch Change";
		}
		
		return "Session Batch not found";
	}

	@Override
	public String updateEnrollmentPayment(Long enrollId) {
CoachingEnrollment enrollel= coachingEnrollmentDAO.findById(enrollId).orElse(null);
		
		if(enrollel!=null) {
		
		if(enrollel.getPaymentType().name()=="PAYMENT_PAINDING") {
			
			enrollel.setPaymentType(PaymentStatus.PAYMENT_DONE);
			
		}
		coachingEnrollmentDAO.save(enrollel);  
		return "Payment Done Successfully!!!";
	}
		
		return "Payment Failed";
	}

	
	
	
}
