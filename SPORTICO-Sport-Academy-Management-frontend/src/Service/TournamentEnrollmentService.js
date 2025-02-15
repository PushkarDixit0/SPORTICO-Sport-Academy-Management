import axios from 'axios';

const BASE_URL = "http://localhost:8080/enrollment/";
const token = JSON.parse(localStorage.getItem("jwtToken"));

class TournamentEnrollmentService {
    
    getAllEnrollments() {
        if (!token) {
            console.error("No token found");
            return Promise.reject("No token found"); 
        }
        return axios.get(BASE_URL, this.getHeaders());
    }

    enrollInTournament(tid, uid) {
        return axios.post(`${BASE_URL}${tid}/${uid}`, {}, this.getHeaders());
    }

    getEnrolledTournaments(uid) {
        return axios.get(`${BASE_URL}enrollments/${uid}`, this.getHeaders());
    }

    getHeaders() {
        const token = JSON.parse(localStorage.getItem("jwtToken")); 
        return token ? { headers: { Authorization: `Bearer ${token}` } } : {};
    }
}

export default new TournamentEnrollmentService();
