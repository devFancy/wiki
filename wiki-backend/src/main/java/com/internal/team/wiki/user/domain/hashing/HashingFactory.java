package com.internal.team.wiki.user.domain.hashing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashingFactory {

    @Bean
    public HashingI getHashing() {
        return new Hashing();
    }
}
