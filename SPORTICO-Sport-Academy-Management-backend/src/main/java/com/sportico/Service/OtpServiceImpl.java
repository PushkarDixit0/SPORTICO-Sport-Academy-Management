package com.sportico.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
public class OtpServiceImpl implements OtpService {
    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Random random = new Random();

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String generateOTP(String email) {
        String otp = String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
        otpStorage.put(email, otp);

        System.out.println("Generated OTP for " + email + ": " + otp); // ✅ Debug Log

        // Send Email
        sendOtpEmail(email, otp);

        return otp;
    }

    private void sendOtpEmail(String email, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Your OTP Code");
            helper.setText("Your OTP for login is: " + otp, true);

            mailSender.send(message);
            System.out.println("✅ OTP sent to email: " + email); 
        } catch (MessagingException e) {
            System.out.println("❌ Failed to send OTP email: " + e.getMessage());
        }
    }

    @Override
    public boolean verifyOTP(String email, String otp) {
    	System.out.println("in verify");
        return otpStorage.containsKey(email) && otpStorage.get(email).equals(otp);
    }

    @Override
    public void invalidateOTP(String email) {
        otpStorage.remove(email);
    }
}
