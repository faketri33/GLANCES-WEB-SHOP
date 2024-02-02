package com.faketri.market.service.user;

import com.faketri.market.domain.users.User;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserImpl userImpl;

    public User findByLogin(String login) {
        var user = userImpl.findByLogin(login)
                           .orElseThrow(() -> new ResourceNotFoundException(
                                   "User with login not found " + login));
        System.out.println(user);
        return user;
    }

    public User findById(Long id) {
        return userImpl.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException(
                               "User with id " + id + " not found"));
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext()
                                            .getAuthentication()
                                            .getName();
        return findByLogin(username);
    }

    public User save(User user) {
        return userImpl.save(user);
    }

}
