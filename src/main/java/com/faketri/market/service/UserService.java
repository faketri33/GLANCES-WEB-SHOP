package com.faketri.market.service;

import com.faketri.market.domain.users.User;
import com.faketri.market.domain.users.ERole;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserImpl userImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(Long id){
        return userImpl.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
    }

    public User save(User user){
        user.getRole().add(ERole.STANDART);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userImpl.save(user);
    }
}
