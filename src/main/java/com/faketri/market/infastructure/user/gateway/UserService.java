package com.faketri.market.infastructure.user.gateway;

import com.faketri.market.entity.user.model.Users;

public interface UserService {

    Users findByLogin(String login);

    Users findById(Long id);

    Users getCurrentUser();

    Users save(Users users);

}
