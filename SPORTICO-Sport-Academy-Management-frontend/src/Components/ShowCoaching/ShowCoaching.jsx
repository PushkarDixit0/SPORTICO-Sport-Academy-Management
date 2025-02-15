import React, { useEffect, useState } from 'react';
import styles from './ShowCoaching.module.css';
import CoachingCard from './CoachingCard/CoachingCard';
import CoachingSessionService from '../../Service/CoachingSessionService';
import sportService from '../../Service/SportService'; 
import userService from '../../Service/UserService';
import coachingEnrollService from '../../Service/CoachingEnrollService';
import Navbar from '../Navbar/Navbar';

function ShowCoaching() {
  // eslint-disable-next-line no-unused-vars
  const [enrollFlag, setEnrollFlag] = useState(false);
  const [coachingData, setCoachingData] = useState([]);
  const [sports, setSports] = useState([]);
  const [coaches, setCoaches] = useState([]);
  const [enrolledSessionIds, setEnrolledSessionIds] = useState(new Set()); // Store enrolled session IDs
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [searchSport, setSearchSport] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userId = localStorage.getItem("id");

        const [coachingRes, sportsRes, coachesRes, enrollmentsRes] = await Promise.all([
          CoachingSessionService.getAllSessions(),
          sportService.getAllSports(),
          userService.getAllCoaches(),
          coachingEnrollService.getAllEnrollmentsById(userId) // Get enrolled session IDs
        ]);

        if (coachingRes.status === 200 && Array.isArray(coachingRes.data)) {
          setCoachingData(coachingRes.data);
        } else {
          console.error('Invalid coaching data format:', coachingRes.data);
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

        if (enrollmentsRes.status === 200 && Array.isArray(enrollmentsRes.data)) {
          const enrolledIds = new Set(enrollmentsRes.data.map(enroll => enroll.coachingSessionId));
          setEnrolledSessionIds(enrolledIds);
        } else {
          console.error('Invalid enrollment data format:', enrollmentsRes.data);
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

  if (loading) return <p>Loading coaching sessions...</p>;

  const availableCoachingSessions = coachingData.filter(session =>
    !enrolledSessionIds.has(session.id) && 
    (searchSport === "" || session.sportName?.toLowerCase().includes(searchSport.toLowerCase()))
  );
  

  return (
    <div>
      <Navbar setSearchSport={setSearchSport}/>
      <div className={styles.coachingContainer}>
        <div className={styles.coachingList}>
          {error ? (
            <p className={styles.errorMessage}>{error}</p>
          ) : availableCoachingSessions.length > 0 ? (
            availableCoachingSessions.map((coaching) =>
              coaching && coaching.id ? (
                <CoachingCard 
                  key={coaching.id} 
                  coaching={coaching} 
                  sports={sports} 
                  coaches={coaches}
                  EnrollFlag={enrollFlag}
                />
              ) : null
            )
          ) : (
            <p>No available coaching sessions.</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default ShowCoaching;
