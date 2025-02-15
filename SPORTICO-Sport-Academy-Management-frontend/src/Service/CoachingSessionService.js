import axios from 'axios';

// Base URL for all session-related requests
const url = "http://localhost:8080/session";


class CoachingSessionService {
  // Fetch all coaching sessions
  getAllSessions() {
    return axios.get(url + "/allsession",this.getHeaders()); // Correct path for all sessions
  }

  // Fetch all coaching sessions for a specific coach (by coach ID)
  getSessionsByCoachId(cid) {
    return axios.get(url + `/allsessionCID/${cid}`,this.getHeaders()); // Correct path for sessions by coach ID
  }

  // Fetch all coaching sessions for a specific sport (by sport ID)
  getSessionsBySportId(sid) {
    return axios.get(url + `/allsessionSID/${sid}`,this.getHeaders()); 
  }

  addSession(session) {
    return axios.post(url + "/savesession", session,this.getHeaders()); 
  }

  getHeaders() {
    try {
        const tokenString = localStorage.getItem("jwtToken");
        const token = tokenString ? JSON.parse(tokenString) : null;
        return token ? { headers: { Authorization: `Bearer ${token}` } } : {};
    } catch (error) {
        console.error("Error parsing JWT token:", error);
        return {};
    }
}
}


const coachingSessionService = new CoachingSessionService();

export default coachingSessionService;
