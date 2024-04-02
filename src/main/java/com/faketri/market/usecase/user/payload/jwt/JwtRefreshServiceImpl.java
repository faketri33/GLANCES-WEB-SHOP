package com.faketri.market.usecase.user.payload.jwt;

import com.faketri.market.entity.user.payload.jwt.gateway.JwtRefreshRepository;
import com.faketri.market.entity.user.payload.jwt.model.JwtRefresh;
import com.faketri.market.infastructure.user.payload.jwt.gateway.JwtRefreshService;

import java.util.Optional;
import java.util.UUID;

public class JwtRefreshServiceImpl implements JwtRefreshService {

    private final JwtRefreshRepository jwtRefreshRepository;

    public JwtRefreshServiceImpl(JwtRefreshRepository jwtRefreshRepository) {
        this.jwtRefreshRepository = jwtRefreshRepository;
    }

    @Override
    public Optional<JwtRefresh> findById(UUID uuid) {
        return jwtRefreshRepository.findById(uuid);
    }

    @Override
    public Optional<JwtRefresh> findByUserLogin(String login) {
        return jwtRefreshRepository.findByUser_Login(login);
    }

    @Override
    public JwtRefresh save(JwtRefresh save) {
        return jwtRefreshRepository.save(save);
    }
}
