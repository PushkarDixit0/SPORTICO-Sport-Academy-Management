package com.sportico.pojos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; 

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private LocalDate createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public BasicEntity() {}

    public Long getId() { 
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "BasicEntity [id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
    }
}
