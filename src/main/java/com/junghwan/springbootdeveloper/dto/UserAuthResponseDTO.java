package com.junghwan.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class UserAuthResponseDTO extends User implements OAuth2User {

    private String userId;
    private String email;
    private String password;
    private String nickName;
    private boolean social;
    private String profileImg;
    private Map<String, Object> attr;

    public UserAuthResponseDTO(String username,
                               String password,
                               boolean social,
                               String profileImg,
                               Collection<? extends GrantedAuthority> authorities,
                               Map<String, Object> attributes) {
        this(username, password, social, profileImg, authorities);
        this.attr = attributes;
    }

    public UserAuthResponseDTO(String username,
                               String password,
                               boolean social,
                               String profileImg,
                               Collection<? extends GrantedAuthority> authorities
                               ) {
        super(username, password, authorities);
        this.email = username;
        this.userId = username;
        this.password = password;
        this.social = social;
        this.profileImg = profileImg;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }

    @Override
    public String getName() {
        return null;
    }
}
