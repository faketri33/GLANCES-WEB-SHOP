package com.faketri.market.entity.user.gateway;

import com.faketri.market.entity.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * Find by login optional.
     *
     * @param login the login
     *
     * @return the optional
     */
    Optional<Users> findByLogin(String login);

    @Override
    Optional<Users> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

}