package com.internal.team.wiki.global.config

import com.internal.team.wiki.global.hashing.Hashing
import com.internal.team.wiki.global.hashing.HashingImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
@Configuration
class HashingConfig {

    @Bean
    fun hashing(): Hashing {
        return HashingImpl()
    }
}

