import axios from "axios";

const BASE_URL = "http://localhost:8080/otp"; 

class OtpService {
    async generateOtp(email) {
        try {
            const response = await axios.post(`${BASE_URL}/generate`, null, {
                params: { email },
            });
            return response.data;
        } catch (error) {
            console.error("Error generating OTP:", error);
            return "Failed to generate OTP";
        }
    }

    async verifyOtp(email, otp) {
        try {
            const response = await axios.post(`${BASE_URL}/verify`, null, {
                params: { email, otp },
            });
            return response.data;
        } catch (error) {
            console.error("Error verifying OTP:", error);
            return "Failed to verify OTP";
        }
    }
}

const otpService = new OtpService();
export default otpService;
