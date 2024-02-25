package com.faketri.market.infastructure.user.gateway;

import com.faketri.market.entity.user.model.Users;
import com.faketri.market.infastructure.config.exception.ResourceNotFoundException;
import com.faketri.market.entity.user.gateway.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class UserService {

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
                                   "User with login not found " + login));
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
        return userImpl.save(users);
    }

}
