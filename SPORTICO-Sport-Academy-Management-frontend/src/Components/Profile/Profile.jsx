import React, { useEffect, useState } from "react";
import styles from "./Profile.module.css";
import UserService from "../../Service/UserService";
import OtpService from "../../Service/OtpService";
import { toast } from "react-toastify";

const Profile = () => {
  const [user, setUser] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editableUser, setEditableUser] = useState(null);
  const [otp, setOtp] = useState("");
  const [isOtpSent, setIsOtpSent] = useState(false);

  useEffect(() => {
    UserService.getSingleUser(JSON.parse(localStorage.getItem("id")))
      .then((response) => {
        setUser(response.data);
        setEditableUser(response.data);
      })
      .catch((error) => console.error("Error fetching profile:", error));
  }, []);

  const handleEdit = async () => {
    try {
      const response = await OtpService.generateOtp(user.email);
      if (response === "Failed to generate OTP") throw new Error(response);

      toast.success("OTP sent successfully!");
      setIsOtpSent(true);
      setIsEditing(true);
    } catch (error) {
      console.error("OTP sending error:", error);
      toast.error("Failed to send OTP. Try again.");
    }
  };

  const handleChange = (e) => {
    setEditableUser({ ...editableUser, [e.target.name]: e.target.value });
  };

  const handleSave = async () => {
    if (!otp.trim()) {
      toast.error("Please enter the OTP.");
      return;
    }

    try {
      const otpResponse = await OtpService.verifyOtp(user.email, otp);
      if (otpResponse !== "OTP Verified Successfully!") {
        toast.error("Invalid OTP. Please try again.");
        return;
      }

      const response = await UserService.updateUser(editableUser);
      console.log(response);
      setUser(editableUser);
      setIsEditing(false);
      setIsOtpSent(false);
      toast.success("Profile updated successfully!");
    } catch (error) {
      console.error("Error updating profile:", error);
      toast.error("Failed to update profile.");
    }
  };

  if (!user) {
    return <div className={styles.loading}>Loading profile...</div>;
  }

  return (
    <div className={styles.container}>
      <div className={styles.profileCard}>
        <div className={styles.header}>Profile</div>
        <div className={styles.details}>
          {["fname", "lname", "email", "mobNo"].map((field) => (
            <div className={styles.row} key={field}>
              <strong>{field === "fname" ? "First Name" : field === "lname" ? "Last Name" : field === "mobNo" ? "Mobile Number" : "Email"} : </strong>
              {isEditing ? (
                <input
                  type="text"
                  name={field}
                  value={editableUser[field]}
                  onChange={handleChange}
                  className={styles.input}
                />
              ) : (
                <span>{user[field]}</span>
              )}
            </div>
          ))}

          {isEditing && (
            <div className={styles.row}>
              <strong>OTP: </strong>
              <input
                type="text"
                name="otp"
                value={otp}
                onChange={(e) => setOtp(e.target.value)}
                className={styles.input}
              />
            </div>
          )}
        </div>

        {isEditing ? (
          <button className={styles.button} onClick={handleSave}>Verify OTP & Save</button>
        ) : (
          <button className={styles.button} onClick={handleEdit}>
            {isOtpSent ? "OTP Sent" : "Update Details"}
          </button>
        )}
      </div>
    </div>
  );
};

export default Profile;
