import React, { useEffect, useState } from "react";
import styles from "./EnrolledUsersModal.module.css";
import TournamentService from "../../Service/TournamentService";

const EnrolledUsersModal = ({ tournamentId, onClose }) => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchEnrolledUsers = async () => {
            try {
                const response = await TournamentService.getEnrolledUsers(tournamentId);
                console.log(response.data)
                setUsers(response.data);
            } catch (error) {
                console.error("Error fetching enrolled users:", error);
            }
        };

        fetchEnrolledUsers();
    }, [tournamentId]);

    return (
        <div className={styles.modalOverlay}>
            <div className={styles.modalContent}>
                <h4 className={styles.heading}>Enrolled Users</h4>
                <ul>
                    {users.length > 0 ? (
                        users.map((user) => <li key={user.id}>{user.name} - {user.email} - {user.mobNo}</li>)
                    ) : (
                        <h6 className={styles.noUsers}>No users enrolled yet.</h6>
                    )}
                </ul>
                <button onClick={onClose} className={styles.closeButton}>Close</button>
            </div>
        </div>
    );
};

export default EnrolledUsersModal;
