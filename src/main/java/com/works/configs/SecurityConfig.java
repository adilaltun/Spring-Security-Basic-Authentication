package com.works.configs;

import com.works.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;

@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }
    //ali@mail.com
    //12345
    //SQL sorgusu ile role tanımlarını security'e bildir.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.encoder());
    }

    //Rollere göre yetkilerin kullanım izinlerini belirle.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()//security için yöntem
                .and()
                .authorizeHttpRequests() //role tanımklarını dikkate al
                .antMatchers("/current/**").hasRole("current")
                .antMatchers("/product/**").hasRole("product")
                .antMatchers("/user/**").permitAll()
                .and()
                .csrf().disable().formLogin().disable();
    }

}
