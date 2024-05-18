package com.junghwan.springbootdeveloper.service;

import com.junghwan.springbootdeveloper.domain.User;
import com.junghwan.springbootdeveloper.domain.UserRole;
import com.junghwan.springbootdeveloper.dto.AddUserRequest;
import com.junghwan.springbootdeveloper.dto.UpdateUserRequest;
import com.junghwan.springbootdeveloper.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class UserService {

    public static class UseridExistException extends Exception{

    }

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;

    public void save(AddUserRequest request){
       User user = User.builder()
                .userId(request.getUserId())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .del(false)
                .social(false)
                .build();

       user.addRole(UserRole.USER);

        userRepository.save(user);
    }

    public void signup(AddUserRequest request) throws UseridExistException{

        String id = request.getUserId();

        boolean exist = userRepository.existsById(id);

        if (exist){
            throw new UseridExistException();
        }

        User user = request.toEntity();
        user.changePassword(passwordEncoder.encode(request.getPassword()));
        user.addRole(UserRole.USER);

        log.info("=======================");
        log.info(user);
        log.info(user.getRoleSet());

        userRepository.save(user);
    }

    @Transactional
    public User update(UpdateUserRequest request, String userId){

        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Not Found Email: " + userId));

        user.update(request.getNickName(), bCryptPasswordEncoder.encode(request.getPassword()), request.getProfileImg());

        return userRepository.save(user);

    }

}
