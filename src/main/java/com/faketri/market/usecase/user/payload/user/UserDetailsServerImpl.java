package com.faketri.market.usecase.user.payload.user;

import com.faketri.market.entity.user.payload.user.model.Users;
import com.faketri.market.infastructure.user.payload.user.gateway.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * The type User details server.
 *
 * @author Dmitriy Faketri
 */

@Service
public class UserDetailsServerImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServerImpl(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        return generateUserDetails(userService.findByLogin(username));
    }

    /**
     * Generate user details user details.
     *
     * @param users the users
     * @return the user details
     */
    public UserDetails generateUserDetails(Users users) {
        return new org.springframework.security.core.userdetails.User(
                users.getLogin(),
                users.getPassword(),
                users.getRole()
                        .stream()
                        .map(x -> new SimpleGrantedAuthority(x.name()))
                        .toList()
        );
    }

}
