package com.faketri.market.repository;

import com.faketri.market.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByLogin(String login);

    @Override
    Optional<Users> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

}