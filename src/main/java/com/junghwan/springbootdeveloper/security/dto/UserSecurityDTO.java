package com.junghwan.springbootdeveloper.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class UserSecurityDTO extends User {

    private String userId;
    private String password;
    private String email;
    private boolean del;
    private boolean social;

    public UserSecurityDTO(String username, String password, String email, boolean del, boolean social, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.userId = username;
        this.email = email;
        this.password = password;
        this.del = del;
        this.social = social;
    }
}
