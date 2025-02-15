import React, { useState } from "react";
import styles from "./AddCoach.module.css";
import Navbar from "../Navbar/Navbar";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import userService from "../../Service/UserService";

const AddCoach = () => {
    const navigate = useNavigate();
    const [userData, setUserData] = useState({
        fname: "",
        lname: "",
        dob: "",
        email: "",
        mobNo: "",
        password: ""
    });

    const [errors, setErrors] = useState({
        fname: "",
        lname: "",
        dob: "",
        email: "",
        mobNo: "",
        password: ""
    });

    const handleChange = (e) => {
        setUserData({ ...userData, [e.target.name]: e.target.value });
        setErrors({ ...errors, [e.target.name]: "" });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newErrors = {};
        for (const key in userData) {
            if (!userData[key].trim()) {
                newErrors[key] = "*Required field";
            }
        }

        const today = new Date();
        const dob = new Date(userData.dob);
        if (dob > today) newErrors.dob = "Date of birth cannot be in the future";

        if (userData.email.trim() && !/^\S+@\S+\.\S+$/.test(userData.email)) {
            newErrors.email = "Invalid email format";
        }

        if (userData.mobNo.trim() && !/^\d{10}$/.test(userData.mobNo)) {
            newErrors.mobNo = "Enter a valid 10-digit number";
        }

        setErrors(newErrors);

        if (Object.keys(newErrors).length === 0) {
            try {
                await userService.saveCoach({ ...userData });
                toast.success("Coach Registered Successfully!");
                navigate('/coaching');
            } catch (error) {
                toast.error("An error occurred while registering the coach.");
                console.error("Error in handleCoachSubmit:", error);
            }
        }
    };

    return (
        <div>
            <Navbar />
            <div className={styles.container}>
                <h2 className={styles.title}>Add New Coach</h2>
                <form onSubmit={handleSubmit} className={styles.form}>
                    <div className={styles.formRow}>
                        <div className={styles.formGroup}>
                            <label htmlFor="fname">First Name</label>
                            <input
                                type="text"
                                name="fname"
                                className={`${styles.input} ${errors.fname ? styles.errorInput : ''}`}
                                value={userData.fname}
                                onChange={handleChange}
                            />
                            {errors.fname && <div className={styles.errorText}>{errors.fname}</div>}
                        </div>

                        <div className={styles.formGroup}>
                            <label htmlFor="lname">Last Name</label>
                            <input
                                type="text"
                                name="lname"
                                className={`${styles.input} ${errors.lname ? styles.errorInput : ''}`}
                                value={userData.lname}
                                onChange={handleChange}
                            />
                            {errors.lname && <div className={styles.errorText}>{errors.lname}</div>}
                        </div>
                    </div>

                    <div className={styles.formGroup}>
                        <label htmlFor="dob">Date of Birth</label>
                        <input
                            type="date"
                            name="dob"
                            className={`${styles.input} ${errors.dob ? styles.errorInput : ''}`}
                            value={userData.dob}
                            onChange={handleChange}
                        />
                        {errors.dob && <div className={styles.errorText}>{errors.dob}</div>}
                    </div>

                    <div className={styles.formGroup}>
                        <label htmlFor="mobNo">Mobile Number</label>
                        <input
                            type="text"
                            name="mobNo"
                            className={`${styles.input} ${errors.mobNo ? styles.errorInput : ''}`}
                            value={userData.mobNo}
                            onChange={handleChange}
                        />
                        {errors.mobNo && <div className={styles.errorText}>{errors.mobNo}</div>}
                    </div>

                    <div className={styles.formGroup}>
                        <label htmlFor="email">Email</label>
                        <input
                            type="email"
                            name="email"
                            className={`${styles.input} ${errors.email ? styles.errorInput : ''}`}
                            value={userData.email}
                            onChange={handleChange}
                        />
                        {errors.email && <div className={styles.errorText}>{errors.email}</div>}
                    </div>

                    <div className={styles.formGroup}>
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            name="password"
                            className={`${styles.input} ${errors.password ? styles.errorInput : ''}`}
                            value={userData.password}
                            onChange={handleChange}
                        />
                        {errors.password && <div className={styles.errorText}>{errors.password}</div>}
                    </div>

                    

                    <button type="submit" className={styles.submit}>Add Coach</button>
                </form>
            </div>
        </div>
    );
};

export default AddCoach;
