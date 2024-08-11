package com.internal.team.wiki.global.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "security.jwt.token")
class JwtProperties {
    lateinit var secretKey: String
    var access: Access = Access()

    class Access {
        var expireLength: Long = 0
    }
}