package com.sportico.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportico.DAO.CoachFeedbackDao;
import com.sportico.DAO.UsersDao;
import com.sportico.DTO.CoachFeedbackdto;
import com.sportico.pojos.CoachFeedback;
import com.sportico.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CoachFeedbackServiceImpl implements CoachFeedbackService {

	@Autowired
	public CoachFeedbackDao coachFeedbackDao;

	@Autowired
	public ModelMapper mapper;

	@Autowired
	public UsersDao userDao;

	@Override
	public List<CoachFeedbackdto> getallFeedback() {
		List<CoachFeedback> allCoachFeedback = coachFeedbackDao.findAll();

		mapper.addConverter(new Converter<User, Long>() {
			public Long convert(MappingContext<User, Long> context) {
				return context.getSource() != null ? context.getSource().getId() : null; // Mapping to only the ID
			}
		});

		return allCoachFeedback.stream().map(feedback -> {
			CoachFeedbackdto dto = mapper.map(feedback, CoachFeedbackdto.class);
			dto.setUser_ID(feedback.getUserID() != null ? feedback.getUserID().getId() : null);
			dto.setCoach_ID(feedback.getCoachID() != null ? feedback.getCoachID().getId() : null);
			return dto;
		}).collect(Collectors.toList());
	}
//		return allCoachFeedback.stream().map(session -> mapper.map(session, CoachFeedbackdto.class))
//				.collect(Collectors.toList());
//
//	}

	@Override
	public String savefeedback(CoachFeedbackdto entity) {
		if (entity != null) {
			CoachFeedback feedback = mapper.map(entity, CoachFeedback.class);

			User user = userDao.findById(entity.getUser_ID()).orElseThrow();
			User coach = userDao.findById(entity.getCoach_ID()).orElseThrow();

			feedback.setUserID(user);
			feedback.setCoachID(coach);

			coachFeedbackDao.save(feedback);

			return "Save Feedback successfully";

		}
		return "Feedback Not Upload";
	}

	@Override
	public String updateMessage(Long fid, String message) {
		CoachFeedback feedback = coachFeedbackDao.findById(fid).orElse(null);
		if (feedback != null) {
			feedback.setMessage(message);
			return "Message Updated Successfully!!!!";
		}
		return "feedback not found";

	}

	@Override
	public List<CoachFeedbackdto> userAllFeedback(Long uid) {

		User user = userDao.findById(uid).orElse(null);

		List<CoachFeedback> allUserCoachFeedback = coachFeedbackDao.findByUserID(user);

		mapper.addConverter(new Converter<User, Long>() {
			public Long convert(MappingContext<User, Long> context) {
				return context.getSource() != null ? context.getSource().getId() : null; // Mapping to only the ID
			}
		});

		return allUserCoachFeedback.stream().map(feedback -> {
			CoachFeedbackdto dto = mapper.map(feedback, CoachFeedbackdto.class);
			dto.setUser_ID(feedback.getUserID() != null ? feedback.getUserID().getId() : null);
			dto.setCoach_ID(feedback.getCoachID() != null ? feedback.getCoachID().getId() : null);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<CoachFeedbackdto> CoachAllFeedback(Long cid) {
		User coach = userDao.findById(cid).orElse(null);

		List<CoachFeedback> allUserCoachFeedback = coachFeedbackDao.findByCoachFeedback(coach);

		mapper.addConverter(new Converter<User, Long>() {
			public Long convert(MappingContext<User, Long> context) {
				return context.getSource() != null ? context.getSource().getId() : null; // Mapping to only the ID
			}
		});

		return allUserCoachFeedback.stream().map(feedback -> {
			CoachFeedbackdto dto = mapper.map(feedback, CoachFeedbackdto.class);
			dto.setUser_ID(feedback.getUserID() != null ? feedback.getUserID().getId() : null);
			dto.setCoach_ID(feedback.getCoachID() != null ? feedback.getCoachID().getId() : null);
			return dto;
		}).collect(Collectors.toList());
	}

}
