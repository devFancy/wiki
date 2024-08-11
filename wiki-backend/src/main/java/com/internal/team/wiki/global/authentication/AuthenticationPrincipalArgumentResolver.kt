package com.internal.team.wiki.global.authentication

import com.internal.team.wiki.auth.dto.LoginUser
import com.internal.team.wiki.auth.service.AuthService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class AuthenticationPrincipalArgumentResolver (
    private val authService: AuthService,

) : HandlerMethodArgumentResolver {

    private val log: Logger = LoggerFactory.getLogger(AuthenticationPrincipalArgumentResolver::class.java)

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val supports = parameter.hasParameterAnnotation(AuthenticationPrincipal::class.java)
        log.debug("AuthenticationPrincipalArgumentResolver supports parameter: $supports")
        return supports
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        log.debug("Resolving argument for AuthenticationPrincipal")
        return try {
            val request = webRequest.getNativeRequest(HttpServletRequest::class.java)
            val accessToken = AuthorizationExtractor.extract(request)
            log.debug("Access token extracted: $accessToken")
            val id = authService.extractUserId(accessToken)
            log.debug("User ID extracted: $id")
            LoginUser(id)
        } catch (e: Exception) {
            log.error("Error resolving argument: ${e.message}", e)
            null
        }
    }

}