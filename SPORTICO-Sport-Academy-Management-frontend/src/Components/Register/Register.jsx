import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import styles from "./Register.module.css";
import UserService from "../../Service/UserService";
import Captcha from "../../Components/Captcha/Captcha";

function Register() {
  const navigate = useNavigate();
  const [recaptchaToken, setRecaptchaToken] = useState(null);

  const [userData, setUserData] = useState({
    fname: "",
    lname: "",
    dob: "",
    email: "",
    mobNo: "",
    password: "",
  });

  const [errors, setErrors] = useState({});

  const onChangeHandler = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value.trim() });
    setErrors({ ...errors, [e.target.name]: "" });
  };

  const validateForm = () => {
    const newErrors = {};
    for (const key in userData) {
      if (!userData[key].trim()) {
        newErrors[key] = "*Required field";
      }
    }

    const today = new Date();
    const dob = new Date(userData.dob);
    if (dob > today) newErrors.dob = "Date of birth cannot be in the future";

    if (userData.email && !/^\S+@\S+\.\S+$/.test(userData.email)) {
      newErrors.email = "Invalid email format";
    }

    if (userData.mobNo && !/^\d{10}$/.test(userData.mobNo)) {
      newErrors.mobNo = "Enter a valid 10-digit mobile number.";
    }

    if (userData.password.length < 6) {
      newErrors.password = "Password must be at least 6 characters.";
    }

    if (!recaptchaToken) {
      newErrors.captcha = "Please complete the reCAPTCHA.";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      const response = await UserService.addUser({ ...userData, recaptchaToken });

      if (response?.data) {
        toast.success("User Registered successfully!");
        
        const userInfo = response.data.user;
        const userDetails = {
          jwtToken: response.data.jwt,
          id: userInfo.id,
          email: userInfo.email,
          role: userInfo.role,
          name: userInfo.name,
        };

        Object.entries(userDetails).forEach(([key, value]) => 
          localStorage.setItem(key, JSON.stringify(value))
        );

        navigate("/");
      } else {
        toast.error("Registration failed. Try again.");
      }
    } catch (error) {
      console.error("Registration error:", error);
      toast.error(error.response?.data?.message || "Error registering user.");
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className={`col-md-8 col-lg-6 ${styles.container1}`}>
          <h3 className={`${styles.title} text-center mb-4`}>Register</h3>
          <form onSubmit={handleSubmit}>
            
            <div className="row">
              <div className="col-md-6 mb-3">
                <label htmlFor="fname">First Name</label>
                <input
                  type="text"
                  id="fname"
                  name="fname"
                  className={`form-control ${errors.fname ? "is-invalid" : ""}`}
                  value={userData.fname}
                  onChange={onChangeHandler}
                />
                {errors.fname && <div className="invalid-feedback">{errors.fname}</div>}
              </div>
              <div className="col-md-6 mb-3">
                <label htmlFor="lname">Last Name</label>
                <input
                  type="text"
                  id="lname"
                  name="lname"
                  className={`form-control ${errors.lname ? "is-invalid" : ""}`}
                  value={userData.lname}
                  onChange={onChangeHandler}
                />
                {errors.lname && <div className="invalid-feedback">{errors.lname}</div>}
              </div>
            </div>

            <div className="row">
              <div className="col-md-6 mb-3">
                <label htmlFor="mobNo">Mobile Number</label>
                <input
                  type="text"
                  id="mobNo"
                  name="mobNo"
                  className={`form-control ${errors.mobNo ? "is-invalid" : ""}`}
                  value={userData.mobNo}
                  onChange={onChangeHandler}
                />
                {errors.mobNo && <div className="invalid-feedback">{errors.mobNo}</div>}
              </div>
              <div className="col-md-6 mb-3">
                <label htmlFor="dob">Date of Birth</label>
                <input
                  type="date"
                  id="dob"
                  name="dob"
                  className={`form-control ${errors.dob ? "is-invalid" : ""}`}
                  value={userData.dob}
                  onChange={onChangeHandler}
                />
                {errors.dob && <div className="invalid-feedback">{errors.dob}</div>}
              </div>
            </div>

            <div className="form-group mb-3">
              <label htmlFor="email">Email</label>
              <input
                type="text"
                id="email"
                name="email"
                className={`form-control ${errors.email ? "is-invalid" : ""}`}
                value={userData.email}
                onChange={onChangeHandler}
              />
              {errors.email && <div className="invalid-feedback">{errors.email}</div>}
            </div>

            <div className="form-group mb-3">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                id="password"
                name="password"
                className={`form-control ${errors.password ? "is-invalid" : ""}`}
                value={userData.password}
                onChange={onChangeHandler}
              />
              {errors.password && <div className="invalid-feedback">{errors.password}</div>}
            </div>

            <div className="d-flex justify-content-center mb-3">
              <Captcha onVerify={(token) => setRecaptchaToken(token)} />
            </div>
            {errors.captcha && <div className="text-danger text-center">{errors.captcha}</div>}

            <button type="submit" className={`submit w-100 ${styles.submit}`}>
              Register
            </button>
          </form>

          <div className="text-center mt-3">
            <p style={{color:'black',textShadow:'none'}}>
              Already have an account?{" "}
              <span className="text-primary" style={{cursor:'pointer'}} onClick={() => navigate("/login")}>
                Sign in
              </span>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;
