package com.internal.team.wiki.auth.service

import com.internal.team.wiki.auth.domain.AuthAccessToken

interface TokenCreator {

    fun createAuthToken(userId: Long?) : AuthAccessToken
}