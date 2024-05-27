package com.junghwan.springbootdeveloper.domain;

import com.junghwan.springbootdeveloper.dto.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@ToString(exclude = "roleSet")
public class User extends BaseEntity implements UserDetails {

    @Id
    @Column(nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String nickName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    private boolean del;

    private boolean social;

    private String profileImg;

    private String socialImg;

//    @Builder
//    public User(String email, String password, boolean del, boolean social, String auth){
//        this.email = email;
//        this.password = password;
//        this.del = del;
//        this.social = social;
//    }

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roleSet = new HashSet<>();

    public void update(String nickName, String password, String profileImg){
        this.nickName = nickName;
        this.password = password;
        this.profileImg = profileImg;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changeDel(boolean del){
        this.del = del;
    }

    public void addRole(UserRole userRole){
        this.roleSet.add(userRole);
    }

    public void clearRoles(){
        this.roleSet.clear();
    }

    public void changeSocial(boolean social){
        this.social = social;
    }

    private void changeProfileImg(String profileImg){
        this.profileImg = profileImg;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 사용자의 id를 반환(고유한 값)
    @Override
    public String getUsername() {
        return email;
    }

    // 사용자의 패스워드를 반환
    @Override
    public String getPassword(){
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;  // ture -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;  // ture -> 잠금되지 않았음
    }

    // 패스워드의 만료 여부 확인
    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;  // true -> 사용 가능
    }
}
