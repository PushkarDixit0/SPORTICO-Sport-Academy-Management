package com.sportico.pojos;

import jakarta.persistence.*;

@Entity
@Table(name = "sports")
public class Sport extends BasicEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SportType type;

    public Sport() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SportType getType() {
        return type;
    }

    public void setType(SportType type) {
        this.type = type;
    }

    public Long getSportId() { 
        return getId(); 
    }

    @Override
    public String toString() {
        return "Sport [id=" + getId() + ", name=" + name + ", type=" + type + "]";
    }
}
