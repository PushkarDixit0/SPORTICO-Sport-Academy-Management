import React, { useState } from "react";
import { generateOtp, verifyOtp } from "../services/otpService";

const OtpComponent = () => {
    const [email, setEmail] = useState("");
    const [otp, setOtp] = useState("");
    const [message, setMessage] = useState("");
    const [isOtpSent, setIsOtpSent] = useState(false);

    const handleGenerateOtp = async () => {
        const response = await generateOtp(email);
        setMessage(response);
        setIsOtpSent(true);
    };

    const handleVerifyOtp = async () => {
        const response = await verifyOtp(email, otp);
        setMessage(response);
        if (response.includes("Successfully")) {
            setIsOtpSent(false); // Reset OTP input on success
        }
    };

    return (
        <div style={{ textAlign: "center", marginTop: "20px" }}>
            <h2>OTP Verification</h2>
            <input
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
            />
            <button onClick={handleGenerateOtp} disabled={isOtpSent}>Generate OTP</button>

            {isOtpSent && (
                <>
                    <input
                        type="text"
                        placeholder="Enter OTP"
                        value={otp}
                        onChange={(e) => setOtp(e.target.value)}
                        required
                    />
                    <button onClick={handleVerifyOtp}>Verify OTP</button>
                </>
            )}

            {message && <p>{message}</p>}
        </div>
    );
};

export default OtpComponent;
