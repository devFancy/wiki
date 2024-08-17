package com.internal.team.wiki.auth.dto

data class AccessTokenResponse(
    val userId: Long?,
    val accessToken: String
) {
}