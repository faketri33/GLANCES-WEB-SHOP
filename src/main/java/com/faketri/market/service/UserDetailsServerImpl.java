package com.faketri.market.service;

import com.faketri.market.domain.users.User;
import com.faketri.market.repository.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServerImpl implements UserDetailsService {
    @Autowired
    private UserImpl userDao;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userDao.findByLogin(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Not found user %s", username))
                );


        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRole().stream()
                        .map(x ->
                                new SimpleGrantedAuthority(x.name())
                        ).toList()
        );
    }
}
