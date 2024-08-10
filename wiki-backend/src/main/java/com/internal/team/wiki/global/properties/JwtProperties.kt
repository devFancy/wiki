package com.internal.team.wiki.global.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "security.jwt.token")
class JwtProperties {
    lateinit var secretKey: String
    var accessExpireLength: Long = 0
}