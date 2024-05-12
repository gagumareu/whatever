package com.junghwan.springbootdeveloper.security;

import com.junghwan.springbootdeveloper.domain.User;
import com.junghwan.springbootdeveloper.domain.UserRole;
import com.junghwan.springbootdeveloper.dto.UserAuthResponseDTO;
import com.junghwan.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class Oauth2UserDetailService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{

        log.info("---------------------------------------------------------");
        log.info("userRequest: " + request);

        String clientName = request.getClientRegistration().getClientName();

        log.info("clientName: " + clientName);
        log.info(request.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(request);

        log.info("===========================");
        oAuth2User.getAttributes().forEach((k,v) -> {
            log.info(k + ":" + v);
        });

        String email = null;
        String socialImg = null;

        if (clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
            socialImg = oAuth2User.getAttribute("picture");
        }

        log.info("EMAIL: " + email);
        log.info("PICTURE: " + socialImg);

        User user = saveSocialUser(email, socialImg);

        UserAuthResponseDTO userAuthResponseDTO = new UserAuthResponseDTO(
                user.getEmail(),
                user.getPassword(),
                true,
                socialImg,
                user.getRoleSet().stream().map(
                        userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name())
                ).collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );

        userAuthResponseDTO.setUserId(email);

        log.info(userAuthResponseDTO);

        return userAuthResponseDTO;
    }

    private User saveSocialUser(String email, String profileImg){

        Optional<User> result = userRepository.findByEmailAndSocial(email, true);

        if (result.isPresent()){
            return result.get();
        }

        User user = User.builder()
                .email(email)
                .userId(email)
                .password(passwordEncoder.encode("1111"))
                .social(true)
                .socialImg(profileImg)
                .build();

        user.addRole(UserRole.USER);

        userRepository.save(user);

        return user;
    }



}
