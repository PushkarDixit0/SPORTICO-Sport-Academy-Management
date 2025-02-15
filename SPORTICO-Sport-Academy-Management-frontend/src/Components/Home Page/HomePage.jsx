import React, { useState, useEffect } from "react";
import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import styles from "./HomePage.module.css"; // Import the CSS module

const sportsImages = [
  "https://img.freepik.com/free-photo/stadium-with-purple-roof-lights-that-says-world-cup-it_1340-34178.jpg?t=st=1739195302~exp=1739198902~hmac=cfc448c8e6209f11bd51b258767ab0ca2e2003bc1fbed5cb7792208ec6012bd8&w=826", // Cricket
  "https://img.freepik.com/free-photo/soccer-players-action-professional-stadium_654080-1130.jpg?t=st=1739195380~exp=1739198980~hmac=76e655948e2f57a5339caa614b2ee062c47fc7cca8b43077d8cdd9e23c9af7fb&w=1060", // Football
  "https://img.freepik.com/free-vector/basketball-court-illuminated-with-stadium-lights-scoreboard-cameras-flashlight_33099-897.jpg?t=st=1739195429~exp=1739199029~hmac=ca90056f5eed8b20a4e42a177193a358147e376c8a101e02e43bf188f717f68a&w=1380", // Basketball
  "https://img.freepik.com/free-photo/digital-art-style-abstract-chess-pieces_23-2151476073.jpg?t=st=1739195539~exp=1739199139~hmac=8c05f0739a2890e4cae7d00fe6d74c149b6bc8b40ab065489346618a211e2ccc&w=1060", // Chess
];

const HomePage = () => {
  const navigate = useNavigate();
  const [currentImage, setCurrentImage] = useState(sportsImages[0]);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImage(sportsImages[Math.floor(Math.random() * sportsImages.length)]);
    }, 5000); 

    return () => clearInterval(interval);
  }, []);

  return (
    <div className={styles.homePage} style={{ backgroundImage: `url(${currentImage})` }}>
      <div className={styles.overlay}></div>
      <div className={styles.content}>
      <div className={styles.authLinks}>
        {!JSON.parse(localStorage.getItem('id')) && (
          <>
          <button onClick={() => navigate("/login")}>Login</button>
            <button onClick={() => navigate("/register")}>Register</button>
            </>
        )}
        </div>
            
        <h1 className={styles.title}>Welcome to Sportico</h1>
        <p className={styles.description}>
          Join thrilling <span className={styles.highlight}>tournaments</span> and elevate your game with expert{" "}
          <span className={styles.coaching}>coaching</span>. Your journey to greatness starts here!
        </p>
        <div className={styles.buttonContainer}>
          <Button variant="contained" color="secondary" size="large" onClick={() => navigate("/tournaments")}>
            Explore Tournaments
          </Button>
          <Button variant="contained" color="primary" size="large" onClick={() => navigate("/coaching")}>
            Join Coaching
          </Button>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
