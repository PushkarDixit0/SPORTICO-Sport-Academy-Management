package com.sportico.Service;

public interface OtpService {

	String generateOTP(String email);

	boolean verifyOTP(String email, String otp);

	void invalidateOTP(String email);

}
