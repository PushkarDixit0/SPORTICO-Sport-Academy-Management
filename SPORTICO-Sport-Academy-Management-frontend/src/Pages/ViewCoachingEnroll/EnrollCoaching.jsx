import React, { useEffect, useState } from 'react';
import Navbar from '../../Components/Navbar/Navbar';
import CoachingCard from '../../Components/ShowCoaching/CoachingCard/CoachingCard';
import sportService from '../../Service/SportService';
import userService from '../../Service/UserService';
import coachingEnrollService from '../../Service/CoachingEnrollService';
import CoachingSessionService from '../../Service/CoachingSessionService';
import styles from '../../Components/ShowCoaching/ShowCoaching.module.css';
import { useNavigate } from 'react-router-dom';

export default function EnrollCoaching() {
  const [enrollFlag, setEnrollFlag] = useState(true);
  const [coachingEnrollments, setCoachingEnrollments] = useState([]);
  const [coachingSessions, setCoachingSessions] = useState([]);
  const [sports, setSports] = useState([]);
  const [coaches, setCoaches] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userId = localStorage.getItem("id");

        const [enrollmentRes, sessionsRes, sportsRes, coachesRes] = await Promise.all([
          coachingEnrollService.getAllEnrollmentsById(userId),
          CoachingSessionService.getAllSessions(),
          sportService.getAllSports(),
          userService.getAllCoaches()

        ]);

        if (enrollmentRes.status === 200 && Array.isArray(enrollmentRes.data)) {
          setCoachingEnrollments(enrollmentRes.data);
         
          
        } else {
          console.error('Invalid enrollment data format:', enrollmentRes.data);
        }

        if (sessionsRes.status === 200 && Array.isArray(sessionsRes.data)) {
          setCoachingSessions(sessionsRes.data);
        } else {
          console.error('Invalid coaching session data format:', sessionsRes.data);
        }

        if (sportsRes.status === 200 && Array.isArray(sportsRes.data)) {
       
            setSports(sportsRes.data);

            
        } else {
          console.error('Invalid sports data format:', sportsRes.data);
        }

        if (coachesRes.status === 200 && Array.isArray(coachesRes.data)) {
           setCoaches(coachesRes.data);
         
         
            
        } else {
          console.error('Invalid coaches data format:', coachesRes.data);
        }
      } catch (err) {
        console.error('Error fetching data:', err);
        setError('Failed to load data.');
      } finally {
               
        setLoading(false);
      }

     
      
      
    };

    fetchData();
  }, []);

  if (loading) return <p>Loading your enrollments...</p>;

  // Map enrollments to session details
  const enrolledCoachingSessions = coachingEnrollments.map((enroll) => {
    const session = coachingSessions.find((session) => session.id === enroll.coachingSessionId);
    return session ? { ...session, paymentType: enroll.paymentType, sportId: enroll.sportId } : null;
  }).filter(Boolean); // Remove null values

  return (
      <div>
        <Navbar></Navbar>
        <h4 className={styles.heading}>Enrolled Coaching Sessions</h4>
        {enrolledCoachingSessions.length > 0 ? (
          <>
            <div className={styles.coachingList}>
              {enrolledCoachingSessions.map((coaching) => (
                <CoachingCard
                  key={coaching.id}
                  coaching={coaching}
                  sports={sports}
                  coaches={coaches}
                  EnrollFlag={enrollFlag}
                />
              ))}
            </div>
            <button onClick={() => navigate("/coaching")} className={styles.backBtn}>
              ⬅️ Back
            </button>
          </>
        ) : (
          <h3 className={styles.msg}>
            You are not enrolled in any Coaching session yet.&nbsp; &nbsp;
            <a href="/coaching" className={styles.animation} onClick={() => navigate("/coaching")}>Enroll Now</a>
          </h3>
        )}
      </div>
    );
    
}
