package com.sportico.DTO;

import java.time.LocalDate;

import com.sportico.pojos.PaymentStatus;

public class TournamentEnrollmentDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long tournamentId;
    private String tournamentName;
    private LocalDate enrollmentDate;
    private PaymentStatus status;

    public TournamentEnrollmentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TournamentEnrollmentDTO [id=" + id + ", userId=" + userId + ", userName=" + userName 
                + ", tournamentId=" + tournamentId + ", tournamentName=" + tournamentName 
                + ", enrollmentDate=" + enrollmentDate + ", status=" + status + "]";
    }
}
