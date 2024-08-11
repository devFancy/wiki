package com.internal.team.wiki.auth.service

import com.internal.team.wiki.exception.InvalidTokenException
import com.internal.team.wiki.global.properties.JwtProperties
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    jwtProperties: JwtProperties
) : TokenProvider {

    private val key: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray(Charsets.UTF_8))
    private val accessTokenValidityInMilliseconds = jwtProperties.access.expireLength

    override fun createAccessToken(payload: String): String {
        return createToken(payload, accessTokenValidityInMilliseconds)
    }

    override fun validateToken(accessToken: String) {
        try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)

            if (claims.body.expiration.before(Date())) {
                throw InvalidTokenException("토큰이 만료되었습니다.")
            }
        } catch (e: JwtException) {
            throw InvalidTokenException("권한이 없습니다.")
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException("권한이 없습니다.")
        }
    }

    override fun getPayLoad(accessToken: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(accessToken)
            .body
            .subject
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