import axios from 'axios';

const BASE_URL = "http://localhost:8080/";

class TournamentService {
    
    getAllTournaments() {
        return axios.get(BASE_URL + "tournament");
    }

    addTournament(tournament) {
        return axios.post(BASE_URL + "tournament", tournament, this.getHeaders());
    }

    updateTournament(tournament) {
        return axios.put(`${BASE_URL}tournament/${tournament.tournamentId}`, tournament, this.getHeaders());
    }

    deleteTournament(id) {
        return axios.delete(`${BASE_URL}tournament/${id}`, this.getHeaders());
    }

    getEnrolledUsers(tournamentId) {
        return axios.get(`${BASE_URL}tournament/${tournamentId}/enrolled-users`, this.getHeaders());
    }

    getHeaders() {
        const token = JSON.parse(localStorage.getItem("jwtToken")); 
        return token ? { headers: { Authorization: `Bearer ${token}` } } : {};
    }
}

export default new TournamentService();
