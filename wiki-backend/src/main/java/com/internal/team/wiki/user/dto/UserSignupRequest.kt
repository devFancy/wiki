package com.internal.team.wiki.user.dto


data class UserSignupRequest(
    val username: String,
    val nickname: String,
    val password: String,
)