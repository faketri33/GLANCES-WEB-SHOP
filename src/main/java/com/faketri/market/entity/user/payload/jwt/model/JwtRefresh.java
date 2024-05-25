package com.faketri.market.entity.user.payload.jwt.model;

import com.faketri.market.entity.user.payload.user.model.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class JwtRefresh {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String token;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfExpiration;

    public JwtRefresh() {
    }

    public JwtRefresh(UUID id, Users user, String token, LocalDateTime dateOfExpiration) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.dateOfExpiration = dateOfExpiration;
    }

    @PrePersist
    public void onCreate() {
        this.dateOfCreate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public LocalDateTime getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(LocalDateTime dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }
}
