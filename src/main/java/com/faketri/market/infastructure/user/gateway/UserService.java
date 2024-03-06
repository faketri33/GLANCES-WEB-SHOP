package com.faketri.market.infastructure.user.gateway;

import com.faketri.market.entity.user.model.Users;

import java.util.UUID;

public interface UserService {

    Users findByLogin(String login);

    Users findById(UUID id);

    Users getCurrentUser();

    Users save(Users users);

}
