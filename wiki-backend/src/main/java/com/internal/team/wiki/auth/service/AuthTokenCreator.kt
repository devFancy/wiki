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
}