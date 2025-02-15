import React, { useEffect, useState } from 'react';
import styles from './ShowTournaments.module.css';
import TournamentService from '../../Service/TournamentService';
import TournamentCard from './TournamentCard/TournamentCard';
import TournamentFormModal from '../Modals/TournamentFormModal';
import { useLocation, useNavigate } from 'react-router-dom';
import TournamentEnrollmentService from '../../Service/TournamentEnrollmentService';

function ShowTournaments({ searchSport }) {
    const [tournaments, setTournaments] = useState([]);
    const [isAdmin, setIsAdmin] = useState(false);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const location = useLocation();
    const navigate = useNavigate();
    
    const userRole = location.state?.role || JSON.parse(localStorage.getItem("role"));
    const userId = JSON.parse(localStorage.getItem('id'));

    useEffect(() => {
        if (userRole === "ROLE_ADMIN") setIsAdmin(true);
        if (userId) setIsLoggedIn(true);
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const allTournamentsResponse = await TournamentService.getAllTournaments();

            let enrolledTournamentIds = new Set();
            if (userId) {
                const enrolledTournamentsResponse = await TournamentEnrollmentService.getEnrolledTournaments(userId);
                if (enrolledTournamentsResponse?.data) {
                    enrolledTournamentIds = new Set(enrolledTournamentsResponse.data.map(t => t.tournamentId));
                }
            }

            const filteredTournaments = allTournamentsResponse?.data?.filter(
                t => !enrolledTournamentIds.has(t.tournamentId) && t.slots > 0
            ) || [];

            setTournaments(filteredTournaments);
        } catch (err) {
            console.error("Error fetching data:", err);
        }
    };

    const handleEnrollClick = () => {
        if (!isLoggedIn) {
            navigate("/login");
        }
    };

    const filteredTournaments = tournaments.filter(tournament =>
        tournament.sportName.toLowerCase().includes(searchSport.toLowerCase())
    );

    return (
        <div>
            <div className={styles.tournamentList}>
                {filteredTournaments.map((tournament) => (
                    <TournamentCard 
                        key={tournament.tournamentId} 
                        tournament={tournament} 
                        isAdmin={isAdmin} 
                        isLoggedIn={isLoggedIn}
                        fetchData={fetchData} 
                        handleEnrollClick={handleEnrollClick} 
                    />
                ))}

                {isAdmin && (
                    <div className={styles.add}>
                        <div className={styles.cardContent}>
                            <button className={styles.addButton} onClick={() => setIsModalOpen(true)} title="Add New">
                                <svg xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    className={styles.addButtonIcon}>
                                    <path d="M12 22C17.5 22 22 17.5 22 12C22 6.5 17.5 2 12 2C6.5 2 2 6.5 2 12C2 17.5 6.5 22 12 22Z" strokeWidth="1.5"></path>
                                    <path d="M8 12H16" strokeWidth="1.5"></path>
                                    <path d="M12 16V8" strokeWidth="1.5"></path>
                                </svg>
                            </button>
                        </div>
                        {isModalOpen && (
                            <TournamentFormModal modalval={isModalOpen} onClose={() => setIsModalOpen(false)} fetchData={fetchData} />
                        )}
                    </div>
                )}
            </div>
        </div>
    );
}

export default ShowTournaments;
