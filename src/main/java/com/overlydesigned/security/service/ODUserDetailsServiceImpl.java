package com.overlydesigned.security.service;

import com.overlydesigned.security.model.ODUserDetails;
import com.overlydesigned.security.entity.User;
import com.overlydesigned.security.repository.UserRepository;
import com.overlydesigned.security.utils.ODConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ODUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByUserName(username);
        result.orElseThrow(() ->
                new UsernameNotFoundException(username + ODConstants.USER_NOT_FOUND)
        );
        return result.map(ODUserDetails::new).get();
    }
}
