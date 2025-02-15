import axios from 'axios';

// Base URL for all sport-related requests
const url = "http://localhost:8080/sports"; // Update with your backend URL

class SportService {
  // Fetch all sports
  getAllSports() {
    return axios.get(url + "/", this.getHeaders()); // Correct path for all sports
  }

  // Create a new sport
  createSport(sport) {
    return axios.post(url + "/", sport, this.getHeaders()); // Correct path for creating a new sport
  }
  
  // Fetch a specific sport by its ID
  getSportById(sportId) {
    return axios.get(url + `/${sportId}`, this.getHeaders()); // Correct path for fetching a sport by ID
  }
  
  // Update a sport by its ID
  updateSport(sportId, updatedSport) {
    return axios.put(url + `/${sportId}`, updatedSport, this.getHeaders()); // Correct path for updating a sport
  }
  
  // Delete a sport by its ID
  deleteSport(sportId) {
    return axios.delete(url + `/${sportId}`, this.getHeaders()); // Correct path for deleting a sport
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

// Create an instance of SportService
const sportService = new SportService();

export default sportService;
