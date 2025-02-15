package com.sportico.pojos;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "tournament_enrollment")
public class TournamentEnrollment extends BasicEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;  

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    private Tournament tournament;  

    @CreationTimestamp
    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus status;

    // Default Constructor
    public TournamentEnrollment() {}

    // Getters & Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
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
        return "TournamentEnrollment [user=" + user.getId() + ", tournament=" + tournament.getId() + 
               ", enrollmentDate=" + enrollmentDate + ", status=" + status + "]";
    }
}
