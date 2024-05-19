package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.User;
import com.junghwan.springbootdeveloper.domain.UserRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


//    @Test
//    public void insertUser(){
//
//        IntStream.rangeClosed(1, 101).forEach(i -> {
//
//            User user = User.builder()
//                    .userId("user" + i)
//                    .email("user" + i + "@email.com")
//                    .password(passwordEncoder.encode("1111"))
//                    .build();
//
//            user.addRole(UserRole.USER);
//
//            if (i > 90){
//                user.addRole(UserRole.ADMIN);
//            }
//            userRepository.save(user);
//
//        });
//
//    }
//
//    @Test
//    public void testRead(){
//
//        Optional<User> result = userRepository.getWithRoles("user100");
//
//        User user = result.orElseThrow();
//
//        log.info(user);
//        log.info(user.getRoleSet());
//
//        user.getRoleSet().forEach(userRole -> log.info(userRole.name()));
//
//    }

}
