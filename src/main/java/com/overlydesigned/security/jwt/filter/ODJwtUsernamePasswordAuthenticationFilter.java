package com.overlydesigned.security.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overlydesigned.security.jwt.UsernamePasswordAuthRequest;
import com.overlydesigned.security.utils.ODJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ODJwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
      //  super.attemptAuthentication(request,response);
        if (!request.getMethod().equals("POST"))
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

        try {
            UsernamePasswordAuthRequest usernamePasswordAuthRequest = new ObjectMapper().readValue(request.getInputStream(), UsernamePasswordAuthRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(usernamePasswordAuthRequest.getUsername(), usernamePasswordAuthRequest.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = ODJwtUtil.createJwtToken(authResult.getName(), authResult.getAuthorities());

        response.addHeader("Authorization", "Bearer " + token);
    }
}
