import React, { useEffect, useState } from 'react';

import feedbackService from '../../../Service/FeedbackService';
import styles from './CoachingCard.module.css';
import coachingEnrollService from '../../../Service/CoachingEnrollService';
import { toast } from 'react-toastify';


const CoachingCard = ({ coaching, sports, coaches, EnrollFlag }) => {
  const [feedbackMsg, setFeedbackMsg] = useState("");
  const [showFeedbackInput, setShowFeedbackInput] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);


  useEffect(() => {
    console.log(coaching);
    const role = JSON.parse(localStorage.getItem("role"))
    setIsAdmin(role === 'ROLE_ADMIN');
  }, [])

  if (!coaching) return null;

  const sport = sports.find(sport => sport.id === coaching.sportID);
  const sportName = sport?.name || "N/A";
  const coach = coaches.find(coach => coach.id === coaching.coachID);
  const coachName = coach ? `Mr. ${coach.fname} ${coach.lname}` : "N/A";

  const formattedDate = coaching.createdOn
    ? new Date(coaching.createdOn).toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' })
    : "Date not available";

    const handleEnrollClick = async () => {
      try {
          const userId = localStorage.getItem("id");
  
          if (!userId) {
              toast.success("User not logged in. Please log in first.");
              return;
          }

          const amt = 25000;
          const name ='Sportico'
  
          var options = {
              key: "rzp_test_KfkSVTMrjRudas",
              key_secret: "WkTAxsYbM61XV2zLioGlRphd",
              currency: "INR",
              amount:  amt * 100,
              name: name,
              description: coaching.description,
              handler: async function (response) {
                  try {
                      const enrollmentData = {
                          userId: Number(userId),
                          coachingSessionId: coaching.id,
                          sportId: sport?.id || "N/A",
                          paymentType: "PAYMENT_PENDING",
                          sportType: sport?.type || "N/A",
                      };
  
                      const enrollResponse = await coachingEnrollService.saveEnrollment(enrollmentData);
  
                      if (enrollResponse?.data) {
                          toast.success("Successfully enrolled in coaching session!");
                      } else {
                          toast.error("Enrollment failed! Please contact support.");
                      }
                  } catch (error) {
                      console.error("Enrollment failed:", error);
                      toast.error("Enrollment failed. Please try again later.");
                  }
              },
              prefill: {
                  name: "Sportico",
                  email: "bdeshpande56@gmail.com",
                  contact: "7058386138",
              },
              notes: {
                  address: "IACSD CDAC Pune, Maharashtra",
              },
              theme: {
                  color: "#b23ba1",
              },
          };
  
          var pay = new window.Razorpay(options);
          pay.open();
      } catch (error) {
          toast.error("An error occurred during the payment process! Please try again.");
      }
  };
  

  // const handleEnrollClick = async () => {
  //   const userId = localStorage.getItem("id");
  //   if (!userId) {
  //     toast.success("User not logged in. Please log in first.");
  //     return;
  //   }

  //   const enrollmentData = {
  //     userId: Number(userId),
  //     coachingSessionId: coaching.id,
  //     sportId: sport?.id || "N/A",
  //     paymentType: "PAYMENT_PENDING",
  //     sportType: sport?.type || "N/A"
  //   };

  //   try {
  //     await coachingEnrollService.saveEnrollment(enrollmentData);
  //     toast.success("Successfully enrolled in coaching session!");
  //   } catch (error) {
  //     console.error("Enrollment failed:", error);
  //     toast.error("Enrollment failed. Please try again later.");
  //   }
  // };

  

  const handleFeedbackSubmit = async () => {
    if (!feedbackMsg.trim()) {
      toast.error("Feedback cannot be empty!");
      return;
    }

    const userId = localStorage.getItem("id");
    if (!userId) {
      toast.error("User not logged in. Please log in first.");
      return;
    }

    const feedbackData = {
      user_ID: Number(userId),
      coach_ID: coaching.coachID,
      message: feedbackMsg,
      date: new Date().toISOString().split('T')[0]
    };

    try {
      await feedbackService.submitFeedback(feedbackData);
      alert("Feedback submitted successfully!");
      setFeedbackMsg("");
      setShowFeedbackInput(false);
    } catch (error) {
      console.error("Feedback submission failed:", error);
      alert("Failed to submit feedback. Please try again later.");
    }
  };

  return (
    <div className={`${styles.card} ${showFeedbackInput ? styles.expandedCard : ''}`}>
      <h3>{coaching.session_Branch ?? "Session Type Not Available"}</h3>
      <p><strong>Venue:</strong> {coaching.venue ?? "Venue not specified"}</p>
      <p><strong>Date:</strong> {formattedDate}</p>
      <p><strong>Sport:</strong> {sportName}</p>
      <p><strong>Coach:</strong> {coachName}</p>

      {!EnrollFlag && !isAdmin && (
        localStorage.getItem("id") ? (
          <button className={styles.enrollButton} onClick={()=>handleEnrollClick()}>
            <span className={styles.btn_shine}>Enroll Now</span>
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



      {EnrollFlag && (
        showFeedbackInput ? (
          <div className={`${styles.feedbackInputContainer} ${styles.feedbackActive}`}>
            <button className={styles.cancelButton} onClick={() => setShowFeedbackInput(false)}>❌</button>
            <input
              type="text"
              value={feedbackMsg}
              onChange={(e) => setFeedbackMsg(e.target.value)}
              placeholder="Enter your feedback..."
              className={styles.feedbackInput}
            />

            <button className={styles.sendButton} onClick={handleFeedbackSubmit}>➤</button>


          </div>
        ) : (
          <button
            className={styles.enrollButton}
            onClick={() => {
              setShowFeedbackInput(true);
            }}
          >
            <span className={styles.btn_shine}>Feedback</span>
          </button>


        )
      )}

    </div>
  );
};

export default CoachingCard;
