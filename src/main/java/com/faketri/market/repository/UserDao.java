package com.faketri.market.repository;

import com.faketri.market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByLogin(String Login);
}
