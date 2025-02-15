import React, { useEffect, useState } from 'react'
import TournamentEnrollmentService from '../../Service/TournamentEnrollmentService';
import TournamentCard from '../ShowTournaments/TournamentCard/TournamentCard';
import styles from '../ShowTournaments/ShowTournaments.module.css'
import { useNavigate } from 'react-router-dom';

function ViewEnrollment() {
    const[enrolled, setEnrolled] = useState([]);

    const navigate = useNavigate();

    useEffect(()=>{
        getEnrolledTournaments();
    },[])

    const getEnrolledTournaments = async()=>{
        const userId = localStorage.getItem("id");
        try{
        const response = await TournamentEnrollmentService.getEnrolledTournaments(userId);
        if(response){
            console.log(response.data);
            setEnrolled(response.data);
        }
    }
    catch(err){
        console.log("error fetching data ",err);
    }  
    }

  return (
    <div>
        <h4 className={styles.heading}>Enrolled Tournaments</h4>
        {enrolled.length>0 ? (
            <>
            <div className={styles.tournamentList}>
            {enrolled.map((tournament) => (
                <TournamentCard key={tournament.tournamentName} tournament={tournament} flg="true"/>
            ))}
        </div>
        <button onClick={()=>navigate("/tournaments")} className={styles.backBtn}> ⬅️ Back</button>
        </>

    ) : 
    (<h3 className={styles.msg}>You are not enrolled in any tournaments yet.&nbsp; &nbsp;
         <a href="/tournaments" className={styles.animation}>Enroll Now</a></h3>)
    }
        
    </div>
  )
}

export default ViewEnrollment