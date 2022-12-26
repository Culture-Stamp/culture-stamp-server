package com.culturestamp.back.auth.config.security;

import com.culturestamp.back.auth.api.service.UserOAuthService;
import com.culturestamp.back.auth.config.filter.JWTRequestFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserOAuthService userOAuthService;
    private final JWTRequestFilter jwtRequestFilter;


    public SecurityConfig(UserOAuthService userOAuthService, JWTRequestFilter jwtRequestFilter) {
        this.userOAuthService = userOAuthService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/swagger/**", "/swagger-resources/**", "/swagger-ui.html").permitAll()
                .antMatchers("/oauth/login/google").permitAll()
                .anyRequest().authenticated()
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("AUTH-TOKEN")
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(userOAuthService);
    }

}
