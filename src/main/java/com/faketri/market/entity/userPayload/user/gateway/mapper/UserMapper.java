package com.faketri.market.entity.userPayload.user.gateway.mapper;

import com.faketri.market.entity.userPayload.user.model.Users;
import com.faketri.market.infastructure.userPayload.user.dto.UserResponse;

public interface UserMapper {

    static UserResponse toResponse(Users user) {
        UserResponse userResponse = new UserResponse(user.getId(),
                user.getEmail(),
                user.getProfileImage(),
                user.getLogin(),
                user.getName(),
                user.getSurname()
        );

        userResponse.setCity(user.getCity());
        userResponse.setDateOfBirthday(user.getDateOfBirthday());
        userResponse.setDateOfCreate(user.getDateOfCreate());
        userResponse.setRole(user.getRole());
        userResponse.setBasket(user.getBasket());
        userResponse.setOrders(user.getOrders());
        userResponse.setFavoriteProduct(user.getFavoriteProduct());

        return userResponse;
    }

    static Users toObj(UserResponse userResponse) {
        Users user = new Users(userResponse.getId(),
                userResponse.getEmail(),
                userResponse.getLogin(),
                null,
                userResponse.getCity()
        );

        user.setCity(userResponse.getCity());
        user.setDateOfBirthday(userResponse.getDateOfBirthday());
        user.setDateOfCreate(userResponse.getDateOfCreate());
        user.setRole(userResponse.getRole());
        user.setBasket(userResponse.getBasket());
        user.setOrders(userResponse.getOrders());
        user.setFavoriteProduct(userResponse.getFavoriteProduct());

        return user;
    }
}
