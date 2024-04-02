package com.faketri.market.infastructure.user.payload.user.gateway;

import com.faketri.market.entity.user.payload.user.model.Users;

import java.util.UUID;

public interface UserService {

    Users findByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsByEmail(String login);

    Users findById(UUID id);

    Users getCurrentUser();

    Users save(Users users);
}
