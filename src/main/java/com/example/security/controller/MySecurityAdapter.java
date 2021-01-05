package com.example.security.controller;

import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class MySecurityAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    private AppAuthenticationSUccessHandler appAuthenticationSUccessHandler;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserService userService;
    @Resource
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login1").hasRole("USER")
//                .antMatchers("/mapper/**").permitAll()
                .anyRequest().authenticated()
//                .antMatchers("/static/**").permitAll()
                .and().formLogin()
                .loginPage("/mylogin").usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/login")
                .successHandler(appAuthenticationSUccessHandler)
                .failureUrl("/mylogin?error").permitAll()

                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new MyPasswordEncoder())
//                .withUser("user")
//                .password("1").roles("USER");
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new MyPasswordEncoder())
//                .withUser("admin")
//                .password("1").roles("ADMIN");
        auth.authenticationProvider(authenticationProvider);
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new MyPasswordEncoder();
    }


}
