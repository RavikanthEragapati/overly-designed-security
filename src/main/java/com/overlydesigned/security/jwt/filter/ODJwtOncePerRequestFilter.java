package com.overlydesigned.security.jwt.filter;

import com.overlydesigned.security.utils.ODJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ODJwtOncePerRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String jwt = null;

            if(StringUtils.hasLength(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
                jwt = authorizationHeader.substring(7);
                username = ODJwtUtil.getUsernameFromToken(jwt);
            }

            if(StringUtils.hasLength(username) && SecurityContextHolder.getContext().getAuthentication() == null){


                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if(ODJwtUtil.isValidJWT(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,
                                    userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }

            }
        filterChain.doFilter(request,response);
    }
}
