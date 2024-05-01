package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "roleSet")
    @Query("SELECT u FROM User u WHERE u.userId = :userId and u.social = false")
    Optional<User> getWithRoles(String userId);




}
