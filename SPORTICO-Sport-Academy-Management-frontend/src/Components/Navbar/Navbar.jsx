import React, { useEffect, useState } from "react";
import styles from "./Navbar.module.css";
import { FaUserCircle } from "react-icons/fa";
import { useNavigate, useLocation } from "react-router-dom";

const Navbar = ({ setSearchSport }) => {
  const navigate = useNavigate();
  const location = useLocation(); // Get the current URL

  const [menuOpen, setMenuOpen] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [userName, setUserName] = useState(null);

  // Update selected section based on current URL
  const selectedSection = location.pathname.includes("coaching")
    ? "Coaching"
    : "Tournaments";

  useEffect(() => {
    const role = JSON.parse(localStorage.getItem("role"));
    const name = JSON.parse(localStorage.getItem("name"));

    if (role === "ROLE_ADMIN") {
      setIsAdmin(true);
    }
    if (name) {
      setUserName(name);
    }
  }, []);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/tournaments");
    window.location.reload();
  };

  const handleEnrollment = () => navigate("/enrollment");
  const handleCoachingEnroll = () => navigate("/allcaochingenroll");
  const handleCoachingInsert = () => navigate("/insertcoaching");


  const handleCoachInsert=()=>{
    navigate("/add-coach")
  }

  return (
    <nav className={styles.navbar}>
      <div className={styles.logo} onClick={() => navigate("/")}>
        <img src="./sportico logo1.png" alt="App Logo" />
      </div>

      <div className={styles.navLinks}>
        <button
          className={`${styles.navButton} ${
            selectedSection === "Tournaments" ? styles.active : ""
          }`}
          onClick={() => navigate("/tournaments")}
        >
          Tournaments
        </button>

        <button
          className={`${styles.navButton} ${
            selectedSection === "Coaching" ? styles.active : ""
          }`}
          onClick={() => navigate("/coaching")}
        >
          Coaching
        </button>
      </div>

      <div className={styles.searchContainer}>
        <input
          type="text"
          placeholder="Search by Sport Name..."
          onChange={(e) => setSearchSport(e.target.value)}
          className={styles.searchInput}
        />
      </div>

      <div className={styles.profileMenu}>
        {userName ? (
          <>
            <h5>Hi, {userName}</h5>
            <FaUserCircle
              className={styles.profileIcon}
              onClick={() => setMenuOpen(!menuOpen)}
            />
            <ul
              className={`${styles.menuDropdown} ${
                menuOpen ? styles.open : ""
              }`}
            >
              <li onClick={() => navigate("/profile")}>Profile</li>

              {isAdmin && (
                <>
                  <li onClick={handleCoachingInsert}>Create Coaching Session</li>
                  {/* <li onClick={handleCoachingEnroll}>Enrolled Coaching</li> */}
                  <li onClick={handleCoachInsert}>Add New Coach</li>
                  
                </>
              )}

              {!isAdmin && (
                <>
                  <li onClick={handleEnrollment}>Enrolled Tournaments</li>
                  <li onClick={handleCoachingEnroll}>Enrolled Coaching</li>
                </>
              )}
              <li onClick={handleLogout}>Logout</li>
            </ul>
          </>
        ) : (
          <div className={styles.authLinks}>
            <button onClick={() => navigate("/login")}>Login</button>
            <button onClick={() => navigate("/register")}>Register</button>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
