package com.faketri.market.service.user;

import com.faketri.market.domain.users.Users;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userImpl;

    public Users findByLogin(String login) {
        var user = userImpl.findByLogin(login)
                           .orElseThrow(() -> new ResourceNotFoundException(
                                   "User with login not found " + login));
        System.out.println(user);
        return user;
    }

    public Users findById(Long id) {
        return userImpl.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException(
                               "User with id " + id + " not found"));
    }

    public Users getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext()
                                            .getAuthentication()
                                            .getName();
        return findByLogin(username);
    }

    public Users save(Users users) {
        return userImpl.save(users);
    }

}
