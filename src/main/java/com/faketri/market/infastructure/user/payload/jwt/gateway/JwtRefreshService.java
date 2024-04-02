package com.faketri.market.infastructure.user.payload.jwt.gateway;

import com.faketri.market.entity.user.payload.jwt.model.JwtRefresh;

import java.util.Optional;
import java.util.UUID;

public interface JwtRefreshService {

    Optional<JwtRefresh> findById(UUID uuid);

    Optional<JwtRefresh> findByUserLogin(String login);

    JwtRefresh save(JwtRefresh save);
}
