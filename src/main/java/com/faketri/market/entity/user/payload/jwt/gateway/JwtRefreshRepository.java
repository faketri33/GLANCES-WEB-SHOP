package com.faketri.market.entity.user.payload.jwt.gateway;

import com.faketri.market.entity.user.payload.jwt.model.JwtRefresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JwtRefreshRepository extends JpaRepository<JwtRefresh, UUID> {
    @Query("select j from JwtRefresh j where j.user.login = ?1")
    Optional<JwtRefresh> findByUser_Login(String login);
}