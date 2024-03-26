package com.faketri.market.usecase.userPayload.user;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.userPayload.user.gateway.repository.UserRepository;
import com.faketri.market.entity.userPayload.user.model.Users;
import com.faketri.market.infastructure.userPayload.user.gateway.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * The type User service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userImpl;

    @Autowired
    public UserServiceImpl(UserRepository userImpl) {
        this.userImpl = userImpl;
    }

    /**
     * Find by login users.
     *
     * @param login the login
     * @return the users
     */
    public Users findByLogin(String login) {
        return userImpl.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Пользователь с логином " + login + " не найден"));
    }

    @Override
    public boolean existsByLogin(String login) {
        return userImpl.existsByLogin(login);
    }

    @Override
    public boolean existsByEmail(String login) {
        return false;
    }

    /**
     * Find by id users.
     *
     * @param id the id
     * @return the users
     */
    public Users findById(UUID id) {
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
     * @return the users
     */
    public Users save(Users users) {
        log.info("User with login - " + users.getLogin() + "  saved");
        return userImpl.save(users);
    }
}
