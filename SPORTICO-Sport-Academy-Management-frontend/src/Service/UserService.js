import axios from 'axios';

const BASE_URL = "http://localhost:8080/";

class UserService {
    getAllUsers() {
        return axios.get(BASE_URL + "user/users", this.getHeaders());
    }

    addUser(user) {
        return axios.post(BASE_URL + "user/adduser", user, this.getHeaders());
    }

    loginUser(user) {
        return axios.post(BASE_URL + "user/login", user, this.getHeaders());
    }

    deleteUser(id) {
        return axios.delete(`${BASE_URL}user/deleteuser/${id}`, this.getHeaders());
    }

    updateUser(user) {
        return axios.put(`${BASE_URL}user/update/${user.id}`, user, this.getHeaders());
    }

    getSingleUser(id){
        return axios.get(BASE_URL + `user/${id}`, this.getHeaders());
    }

    getAllCoaches() {
        return axios.get(`${BASE_URL}user/allrolecoaches`, this.getHeaders()); 
    }

    getAllSpecificUsers() {
        return axios.get(`${BASE_URL}user/specific`, this.getHeaders()); 
    }

    saveCoach(coachData) {
        return axios.post(`${BASE_URL}user/coach/save`, coachData, this.getHeaders());
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

const userService = new UserService();
export default userService;
