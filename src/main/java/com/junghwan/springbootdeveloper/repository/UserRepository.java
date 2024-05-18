package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = "roleSet")
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "roleSet")
    @Query("SELECT u FROM User u WHERE u.userId = :id")
    Optional<User> findByUserId(String id);

    @EntityGraph(attributePaths = "roleSet")
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> getWithRoles(String userId);

    @EntityGraph(attributePaths = "roleSet")
    @Query("SELECT u FROM User u WHERE u.userId = :userId AND u.social = :social")
    Optional<User> findByEmailAndSocial(String userId, boolean social);


}
