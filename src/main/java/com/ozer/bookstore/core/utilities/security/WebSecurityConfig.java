package com.ozer.bookstore.core.utilities.security;

//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //Now, we can write security config settings here
//        http.authorizeRequests().anyRequest().authenticated().and()
//                .httpBasic(); //http basic allowed us here
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("ozerbey").password("{noop} @pa55word").roles("USER");
//    }

//    @Autowired
//    public void configureGlobalEncrypted(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("ozerbey").password(passwordEncoder.encode("@pa55word")).roles("USER");
//    }
//}


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/users").hasRole("manager")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}