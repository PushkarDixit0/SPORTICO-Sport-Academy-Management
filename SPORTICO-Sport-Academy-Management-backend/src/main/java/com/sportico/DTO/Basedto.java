package com.sportico.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class Basedto {
    
    @JsonProperty(access = Access.READ_ONLY)
    private Long id; 

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDate createdOn; 

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime updatedOn; 

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
        return "Basedto [id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
    }
}
