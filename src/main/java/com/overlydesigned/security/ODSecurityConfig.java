package com.overlydesigned.security;

import com.overlydesigned.security.jwt.filter.ODJwtOncePerRequestFilter;
import com.overlydesigned.security.jwt.filter.ODJwtUsernamePasswordAuthenticationFilter;
import com.overlydesigned.security.utils.ODConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration - this annotation is not required as it is covered by @EnableWebSecurity annotation
@EnableWebSecurity
@RequiredArgsConstructor
public class ODSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        /*
        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework, dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(passwordEncoder())
                .passwordAttribute("password");
        */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Cross Side Request Forgery - is not required in our app, as we are not using form based UI.
                .csrf().disable()

                // We are making our app, not to maintain any session info as REST has to be Stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // We are disabling X-Frame-Options as we are not using form based UI
                .headers().frameOptions().disable()
                .and()

                //ODJwtUsernamePasswordAuthenticationFilter gets called when an authentication is attempted by calling "/login"
                .addFilter(new ODJwtUsernamePasswordAuthenticationFilter(authenticationManager()))

                //ODJwtOncePerRequestFilter gets called for every request to validate JWT and sets Authentication value in SecurityContextHolder
                .addFilterAfter(new ODJwtOncePerRequestFilter(userDetailsService),
                        ODJwtUsernamePasswordAuthenticationFilter.class)


                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/*").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/api/dashboard").hasRole("USER")
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new DelegatingPasswordEncoder(ODConstants.idForNoPassEncode, ODConstants.PASSWORD_ENCODER_MAP);
    }

}
