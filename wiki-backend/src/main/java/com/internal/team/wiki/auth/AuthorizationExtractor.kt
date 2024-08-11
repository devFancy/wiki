package com.internal.team.wiki.auth

import com.internal.team.wiki.exception.EmptyAuthorizationHeaderException
import com.internal.team.wiki.exception.InvalidTokenException
import org.springframework.http.HttpHeaders
import javax.servlet.http.HttpServletRequest

object AuthorizationExtractor {

    private const val BEARER_TYPE = "Bearer "

    fun extract(request: HttpServletRequest?): String {
        val authorizationHeader = request?.getHeader(HttpHeaders.AUTHORIZATION)
            ?: throw EmptyAuthorizationHeaderException()

        validateAuthorizationFormat(authorizationHeader)
        return authorizationHeader.substring(BEARER_TYPE.length).trim()
    }

    private fun validateAuthorizationFormat(authorizationHeader: String) {
        if (!authorizationHeader.lowercase().startsWith(BEARER_TYPE.lowercase())) {
            throw InvalidTokenException("token 형식이 잘못 되었습니다.")
        }
    }
}