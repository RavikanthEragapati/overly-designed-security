package com.overlydesigned.security;

import com.overlydesigned.security.utils.ODConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = ODConstants.REPOSITORY_PKG)
public class ODJpaConfig {
}
