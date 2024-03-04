package com.faketri.market.usecase.user;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.user.gateway.UserRepository;
import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.user.gateway.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 *
 * @author Dmitriy Faketri
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userImpl;

    /**
     * Find by login users.
     *
     * @param login the login
     *
     * @return the users
     */
    public Users findByLogin(String login) {
        var user = userImpl.findByLogin(login)
                           .orElseThrow(() -> new ResourceNotFoundException(
                                   "Пользователь с логином " + login + " не найден"));
        System.out.println(user);
        return user;
    }

    /**
     * Find by id users.
     *
     * @param id the id
     *
     * @return the users
     */
    public Users findById(Long id) {
        return userImpl.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException(
                               "User with id " + id + " not found"));
    }

    /**
     * Gets current user.
     *
     * @return the current user
     */
    public Users getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext()
                                            .getAuthentication()
                                            .getName();
        return findByLogin(username);
    }

    /**
     * Save users.
     *
     * @param users the users
     *
     * @return the users
     */
    public Users save(Users users) {
        log.info("User with login - " + users.getLogin() + " saved");
        return userImpl.save(users);
    }

}
