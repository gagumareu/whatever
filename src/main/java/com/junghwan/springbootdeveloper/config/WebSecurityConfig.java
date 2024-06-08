package com.junghwan.springbootdeveloper.config;

import com.junghwan.springbootdeveloper.security.UserDetailService;
import com.junghwan.springbootdeveloper.security.handler.Custom403Handler;
import com.junghwan.springbootdeveloper.security.handler.UserLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@Log4j2
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UserDetailService userService;
    private final DataSource dataSource;
    @Bean
    public WebSecurityCustomizer configure(){

       log.info("-------- web configure ----------");
        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        log.info("---------- configure ------------");

        // 인증, 인가, 설정
//        http.authorizeHttpRequests((authorize) -> {
//            authorize.requestMatchers("/login", "/signup", "/user", "/articles", "/priceCard", "/foodExpiry").permitAll();
//            authorize.anyRequest().authenticated();
//        });

        // 폼 기반 로그인 설정
        http.formLogin(login -> {
//            login.loginPage("/login").defaultSuccessUrl("/articles");
            login.loginPage("/login");
        });

        // 로그아웃 설정
        http.logout(logout -> {
            logout.logoutSuccessUrl("/articles").invalidateHttpSession(true);
        });

        // csrf 비활성화
        http.csrf(AbstractHttpConfigurer::disable);

        // oauth 활성화
        http.oauth2Login(oauth2Login -> oauth2Login.loginPage("/login").successHandler(successHandler()));

        return http.build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService ) throws Exception{

         return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder)
                 .and().build();


    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){

        return  new Custom403Handler();
    }

    @Bean
    public UserLoginSuccessHandler successHandler(){
        return new UserLoginSuccessHandler(bCryptPasswordEncoder());
    }


}
