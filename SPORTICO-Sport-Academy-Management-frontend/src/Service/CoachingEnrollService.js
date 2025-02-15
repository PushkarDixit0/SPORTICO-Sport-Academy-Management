import axios from 'axios';

const BASE_URL = "http://localhost:8080/CoachingEnroll";

class CoachingEnrollService {
    getAllEnrollments() {
        return axios.get(`${BASE_URL}/GetAllEnroll`, this.getHeaders());
    }

    getAllEnrollmentsById(userId) {
        return axios.get(`${BASE_URL}/GetAllEnrollById/${userId}`, this.getHeaders());
    }

    saveEnrollment(enrollment) {
        return axios.post(`${BASE_URL}/SaveEnroll`, enrollment, this.getHeaders());
    }

    deleteEnrollment(enrollId) {
        return axios.delete(`${BASE_URL}/DeleteEnroll/${enrollId}`, this.getHeaders());
    }

    updateEnrollmentSession(enrollId, session) {
        return axios.put(`${BASE_URL}/updateEnrollSession/${enrollId}/${session}`, this.getHeaders());
    }

    updateEnrollmentPayment(enrollId) {
        return axios.patch(`${BASE_URL}/updateEnrollPayment/${enrollId}`, this.getHeaders());
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

const coachingEnrollService = new CoachingEnrollService();
export default coachingEnrollService;