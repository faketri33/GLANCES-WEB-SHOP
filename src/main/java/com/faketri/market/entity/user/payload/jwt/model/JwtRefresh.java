package com.faketri.market.entity.user.payload.jwt.model;

import com.faketri.market.entity.user.payload.user.model.Users;
import jakarta.persistence.*;

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

    public JwtRefresh() {
    }

    public JwtRefresh(UUID id, Users user, String token) {
        this.id = id;
        this.user = user;
        this.token = token;
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
}
