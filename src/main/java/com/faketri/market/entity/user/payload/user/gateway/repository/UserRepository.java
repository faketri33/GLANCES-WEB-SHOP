package com.faketri.market.entity.user.payload.user.gateway.repository;

import com.faketri.market.entity.user.payload.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The interface User repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     */
    Optional<Users> findByLogin(String login);

    Optional<Users> findById(UUID aLong);

    @Query("select (count(u) > 0) from Users u where u.login = ?1")
    boolean existsByLogin(String login);

    @Query("select (count(u) > 0) from Users u where u.email = ?1")
    boolean existsByEmail(String email);


    void deleteById(UUID aLong);

}