package com.overlydesigned.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsernamePasswordAuthRequest {
    private String username;
    private String password;
}
