package com.overlydesigned.security.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ODConstants {

    public static final String REPOSITORY_PKG = "com.overlydesigned.security.repository";

    public static final String idForNoPassEncode = "noop";
    public static final String idForBcryptEncode = "bcrypt";
    public static final String idForPBKDF2Encode = "pbkdf2";
    public static final String idForScryptEncode = "scrypt";

    public static final Map<String, PasswordEncoder> PASSWORD_ENCODER_MAP = buildEncoderMap();

    public static final String USER_NOT_FOUND = " user not found in the system";


    public static Map<String, PasswordEncoder> buildEncoderMap() {
        Map<String, PasswordEncoder> passwordEncoder = new HashMap<String, PasswordEncoder>();
        passwordEncoder.put(idForNoPassEncode, NoOpPasswordEncoder.getInstance());
        passwordEncoder.put(idForBcryptEncode, new BCryptPasswordEncoder());
        passwordEncoder.put(idForPBKDF2Encode, new Pbkdf2PasswordEncoder());
        passwordEncoder.put(idForScryptEncode, new SCryptPasswordEncoder());
        return passwordEncoder;
    }

}
