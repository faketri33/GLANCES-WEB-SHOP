package com.faketri.market.service.user;

import com.faketri.market.domain.users.User;
import com.faketri.market.payload.request.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServerImpl userDetailsServer;

    public User registration(UserDto userDto){
        User user = new User();

        user.setLogin(userDto.login());
        user.setPassword(userDto.password());
        user.setEmail(userDto.email());

        return userService.save(user);
    }
}
