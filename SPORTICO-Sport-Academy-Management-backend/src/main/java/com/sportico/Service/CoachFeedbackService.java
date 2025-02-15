package com.sportico.Service;

import java.util.List;

import com.sportico.DTO.CoachFeedbackdto;

public interface CoachFeedbackService {

	List<CoachFeedbackdto> getallFeedback();

	String savefeedback(CoachFeedbackdto entity);

	String updateMessage(Long fid,String message);

	List<CoachFeedbackdto> userAllFeedback(Long uid);

	List<CoachFeedbackdto> CoachAllFeedback(Long cid);

}
