import React, { useState } from 'react';
import styles from './TournamentCard.module.css';
import TournamentFormModal from '../../Modals/TournamentFormModal';
import EnrolledUsersModal from '../../Modals/EnrolledUsersModal';
import { IconButton } from "@mui/material";
import VisibilityIcon from "@mui/icons-material/Visibility";
import PaymentModal from '../../Modals/PaymentModal';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
// import { useRazorpay } from 'react-razorpay';
import TournamentEnrollmentService from '../../../Service/TournamentEnrollmentService';

const TournamentCard = ({ tournament, flg, isAdmin, fetchData }) => {
    
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isUsersModalOpen, setIsUsersModalOpen] = useState(false);
    const [isPaymentModalOpen, setIsPaymentModalOpen] = useState(false);
    const [selectedTournamentId, setSelectedTournamentId] = useState(null);

    // const Razorpay = useRazorpay();

    const navigate = useNavigate();
    const userId = localStorage.getItem("id");
    const isLoggedIn = Boolean(userId);

    const getSlotColor = (slotsRemaining) => {
        if (slotsRemaining > 5) return "#28a745";
        if (slotsRemaining > 0) return "#ffc107";
        return "#dc3545";
    };

    const handleclickOfCard = async (item) => {
        try {
          var options = {
            key: "rzp_test_KfkSVTMrjRudas",
            key_secret: "WkTAxsYbM61XV2zLioGlRphd",
            currency: "INR",
            amount: item.price * 100,
            name: item.tournamentName,
            description: item.tournamentDescription,
            handler: function (response) {
              toast("Successfully Enrolled in Tournament!!");
              navigate("/tournaments");
            },
            prefill: {
              name: "Sportico",
              email: "bdeshpande56@gmail.com",
              contact: "7058386138",
            },
            notes: {
              address: "IACSD CDAC pune, Maharashtra",
            },
            theme: {
              color: "#b23ba1",
            },
          };
      
          var pay = new window.Razorpay(options);
          pay.open();
      
          try {
            const tournamentId = item.tournamentId;
            const userId = JSON.parse(localStorage.getItem("id"));
      
            const response = await TournamentEnrollmentService.enrollInTournament(
              tournamentId,
              userId
            );
      
            if (response?.data) {
              console.log(response.data);
              toast.success("Enrollment successful!");
              fetchData();
            } else {
              toast.error("Enrollment failed! Please contact support.");
            }
          } catch (error) {
            toast.error("An error occurred while enrolling! Please try again.");
          }
        } catch (error) {
          toast.error(
            "An error occurred during the payment process! Please try again."
          );
        }
      };
      
    
    

    return (
        <>
            <div className={styles.card}>
                {!flg && (
                    <div className={styles.slotsRemaining}
                        style={{ backgroundColor: getSlotColor(tournament.slots) }}>
                        {tournament.slots} Slots Left
                    </div>
                )}

                <h3 className={styles.heading}>{tournament.tournamentName}</h3>
                <p className={styles.text}>{tournament.tournamentDescription}</p>
                <p className={styles.text}><strong>Venue:</strong> {tournament.tournamentVenue}</p>
                <p className={styles.text}><strong>Date:</strong> {tournament.tournamentDate}</p>
                <p className={styles.text}><strong>Time:</strong> {tournament.tournamentTime}</p>

                {/* Admin Controls */}
                {isAdmin && isLoggedIn && (
                    <>
                        <IconButton
                            className={styles.view}
                            onClick={() => setIsUsersModalOpen(true)}
                            sx={{
                                backgroundColor: "#7a0066",
                                color: "#fff",
                                "&:hover": { backgroundColor: "rgb(194, 26, 121)" }
                            }}
                        >
                            <VisibilityIcon />
                        </IconButton>
                        <button className={styles.editBtn} onClick={() => setIsModalOpen(true)}>
                            <svg height="1em" viewBox="0 0 512 512">
                                <path d="M410.3 231l11.3-11.3-33.9-33.9-62.1-62.1L291.7 89.8l-11.3 11.3-22.6 22.6L58.6 322.9c-10.4 10.4-18 23.3-22.2 37.4L1 480.7c-2.5 8.4-.2 17.5 6.1 23.7s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L387.7 253.7 410.3 231zM160 399.4l-9.1 22.7c-4 3.1-8.5 5.4-13.3 6.9L59.4 452l23-78.1c1.4-4.9 3.8-9.4 6.9-13.3l22.7-9.1v32c0 8.8 7.2 16 16 16h32zM362.7 18.7L348.3 33.2 325.7 55.8 314.3 67.1l33.9 33.9 62.1 62.1 33.9 33.9 11.3-11.3 22.6-22.6 14.5-14.5c25-25 25-65.5 0-90.5L453.3 18.7c-25-25-65.5-25-90.5 0zm-47.4 168l-144 144c-6.2 6.2-16.4 6.2-22.6 0s-6.2-16.4 0-22.6l144-144c6.2-6.2 16.4-6.2 22.6 0s6.2 16.4 0 22.6z"></path>
                            </svg>
                        </button>
                    </>
                )}

                {(!flg && !isAdmin) && (
                    localStorage.getItem("id") ? (
                        <button
                            className={styles.enrollButton}
                            onClick={() => {
                                setSelectedTournamentId(tournament.tournamentId);
                                handleclickOfCard(tournament);
                                // setIsPaymentModalOpen(true);
                            }}
                            disabled={isPaymentModalOpen}
                        >
                            <span className={styles.btn_shine}>Pay & Enroll</span>
                        </button>
                    ) : (
                        <button
                            className={styles.enrollButton}
                            onClick={() => {
                                window.location.href = "/login";
                            }}
                        >
                            <span className={styles.btn_shine}>Login to Enroll</span>
                        </button>
                    )
                )}

            </div>

            {isModalOpen && (
                <TournamentFormModal
                    existingData={tournament}
                    onClose={() => setIsModalOpen(false)}
                    edit={true}
                    fetchData={fetchData}
                />
            )}

            {isUsersModalOpen && (
                <EnrolledUsersModal
                    tournamentId={tournament.tournamentId}
                    onClose={() => setIsUsersModalOpen(false)}
                />
            )}

            {isPaymentModalOpen && (
                <PaymentModal
                    tournamentId={selectedTournamentId}
                    userId={localStorage.getItem("id")}
                    onClose={() => setIsPaymentModalOpen(false)}
                    onSuccess={fetchData}
                />
            )}
        </>
    );
};

export default TournamentCard;
