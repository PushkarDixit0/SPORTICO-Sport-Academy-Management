import axios from 'axios';

const BASE_URL = "http://localhost:8080/feedback"; // Update if needed

class FeedbackService {
  
  // Fetch all feedback
  getAllFeedback() {
    return axios.get(`${BASE_URL}/AllFeedback`, this.getHeaders());
  }

  // Submit new feedback
  submitFeedback(feedbackData) {
    return axios.post(`${BASE_URL}/savefeedback`, feedbackData, this.getHeaders());
  }

  // Update feedback message
  updateFeedbackMessage(feedbackId, message) {
    return axios.patch(`${BASE_URL}/updateMessage/${feedbackId}`, message, this.getHeaders());
  }

  // Get feedback by user ID
  getUserFeedback(userId) {
    return axios.get(`${BASE_URL}/FeedbackByuid/${userId}`, this.getHeaders());
  }

  // Get all feedback for a specific coach
  getCoachFeedback(coachId) {
    return axios.get(`${BASE_URL}/AllFeedbacksOfCid/${coachId}`, this.getHeaders());
  }

  // Helper function to attach JWT token in headers
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

// Create an instance of FeedbackService
const feedbackService = new FeedbackService();

export default feedbackService;
