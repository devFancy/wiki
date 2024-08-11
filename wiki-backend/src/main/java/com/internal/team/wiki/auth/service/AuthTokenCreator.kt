package com.internal.team.wiki.auth.service

import com.internal.team.wiki.auth.domain.AuthAccessToken
import org.springframework.stereotype.Component

@Component
class AuthTokenCreator(
    private val tokenProvider: TokenProvider
): TokenCreator {

    override fun createAuthToken(userId: Long?) : AuthAccessToken {
        val accessToken: String = tokenProvider.createAccessToken(userId.toString())

        return AuthAccessToken(accessToken)
    }

    override fun extractPayLoad(accessToken: String): Long {
        tokenProvider.validateToken(accessToken)
        return tokenProvider.getPayLoad(accessToken).toLong()
    }
}

private fun Any.toLong(): Long {
    return when (this) {
        is String -> this.toLongOrNull() ?: throw NumberFormatException("Cannot convert string to long")
        is Number -> this.toLong()
        else -> throw IllegalArgumentException("Unsupported type for conversion to Long")
    }
}
