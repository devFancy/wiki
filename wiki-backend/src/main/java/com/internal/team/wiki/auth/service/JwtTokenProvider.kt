package com.internal.team.wiki.auth.service

import com.internal.team.wiki.global.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) : TokenProvider {

    private val key: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray(Charsets.UTF_8))
    private val accessTokenValidityInMilliseconds = jwtProperties.accessExpireLength

    override fun createAccessToken(payLoad: String): String {
        return createToken(payLoad, accessTokenValidityInMilliseconds)
    }

    fun createToken(payLoad: String, validityInMilliseconds: Long): String {
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)

        return Jwts.builder()
            .setSubject(payLoad)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }
}