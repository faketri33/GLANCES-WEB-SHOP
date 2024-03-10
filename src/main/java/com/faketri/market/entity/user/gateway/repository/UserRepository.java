package com.faketri.market.entity.user.gateway.repository;

import com.faketri.market.entity.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The interface User repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     */
    Optional<Users> findByLogin(String login);

    Optional<Users> findById(UUID aLong);

    void deleteById(UUID aLong);

}