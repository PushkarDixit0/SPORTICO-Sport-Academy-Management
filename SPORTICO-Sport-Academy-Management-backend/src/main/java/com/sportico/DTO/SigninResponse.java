package com.sportico.DTO;

public class SigninResponse {
    private String jwt;
    private String mesg;
    private LoginResponseDTO user;

    // Constructor
    public SigninResponse(String jwt, String mesg, LoginResponseDTO user) {
        this.jwt = jwt;
        this.mesg = mesg;
        this.user = user;
    }

    // Getters & Setters
    public String getJwt() { return jwt; }
    public String getMesg() { return mesg; }
    public LoginResponseDTO getUser() { return user; }
}
