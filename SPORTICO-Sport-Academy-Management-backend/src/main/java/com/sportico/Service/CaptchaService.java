//package com.sportico.Service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@Service
//public class CaptchaService {
//
//    @Value("${recaptcha.secret.key}")
//    private String secretKey;
//
//    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
//
//    public boolean verifyCaptcha(String captchaResponse) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Send a POST request to the Google reCAPTCHA API for verification
//        String url = UriComponentsBuilder.fromHttpUrl(RECAPTCHA_VERIFY_URL)
//                                        .queryParam("secret", secretKey)
//                                        .queryParam("response", captchaResponse)
//                                        .toUriString();
//
//        CaptchaResponse response = restTemplate.postForObject(url, null, CaptchaResponse.class);
//
//        // Check if CAPTCHA verification was successful
//        return response != null && response.isSuccess();
//    }
//
//    // Inner class to map the response from Google reCAPTCHA API
//    private static class CaptchaResponse {
//        private boolean success;
//
//        public boolean isSuccess() {
//            return success;
//        }
//
//        public void setSuccess(boolean success) {
//            this.success = success;
//        }
//    }
//}
//
