package com.faketri.market.service.user;

import com.faketri.market.domain.users.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Getter
@Service
public class UserDetailsServerImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws
                                                           UsernameNotFoundException {
        return generateUserDetails(userService.findByLogin(username));
    }

    public UserDetails generateUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRole()
                    .stream()
                    .map(x -> new SimpleGrantedAuthority(x.name()))
                    .toList()
        );
    }

}
