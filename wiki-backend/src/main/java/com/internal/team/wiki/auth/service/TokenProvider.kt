package com.internal.team.wiki.auth.service

interface TokenProvider {

    fun createAccessToken(payload: String): String
}