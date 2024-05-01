package com.junghwan.springbootdeveloper.security;

import com.junghwan.springbootdeveloper.domain.User;
import com.junghwan.springbootdeveloper.repository.UserRepository;
import com.junghwan.springbootdeveloper.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUserName: " + username);

        Optional<User> result = userRepository.getWithRoles(username);

        if (result.isEmpty()){
            throw new UsernameNotFoundException("username not found");
        }

        User user = result.get();

        UserSecurityDTO userSecurityDTO = new UserSecurityDTO(
                user.getUserId(),
                user.getPassword(),
                user.getEmail(),
                user.isDel(),
                false,
                user.getRoleSet().stream().map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name()))
                        .collect(Collectors.toList())
        );

        log.info("--------- userSecurityDTO -------------");
        log.info(userSecurityDTO);

        return userSecurityDTO;
    }



}
