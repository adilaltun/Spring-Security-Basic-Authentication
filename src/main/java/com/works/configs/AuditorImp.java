package com.works.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditorImp implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        auth.getName();
        Optional<String> optional = Optional.of(auth.getName());
        System.out.println(optional.get());
        return optional;
    }
}
