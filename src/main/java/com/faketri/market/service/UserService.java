package com.faketri.market.service;

import com.faketri.market.entity.User;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(Long id){
        return userDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
    }

    public void save(User user){
        userDao.save(user);
    }
}
