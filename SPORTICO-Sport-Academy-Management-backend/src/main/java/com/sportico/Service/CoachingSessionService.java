package com.sportico.Service;

import java.util.List;

import com.sportico.DTO.CoachingSessiondto;
import com.sportico.DTO.CoachingSessiondtoPost;
import com.sportico.pojos.CoachingSession;

public interface CoachingSessionService{

	List<CoachingSessiondto> getallSession();

	CoachingSession savesession(CoachingSessiondtoPost entity);

	List<CoachingSessiondto> getallSessionCID(Long cid);
//
	List<CoachingSessiondto> getallSessionSID(Long sid);

}
