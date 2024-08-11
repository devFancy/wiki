package com.internal.team.wiki.auth.service

import com.internal.team.wiki.auth.AuthRepository
import com.internal.team.wiki.auth.domain.AuthAccessToken
import com.internal.team.wiki.auth.dto.AccessTokenResponse
import com.internal.team.wiki.auth.dto.LoginRequest
import com.internal.team.wiki.auth.dto.LoginUser
import com.internal.team.wiki.exception.NotFoundUserException
import com.internal.team.wiki.global.hashing.Hashing
import com.internal.team.wiki.exception.fail
import com.internal.team.wiki.user.UserRepository
import com.internal.team.wiki.user.domain.Password
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AuthService (
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val hashing: Hashing,
    private val tokenCreator: TokenCreator,
) {
    fun login(loginRequest: LoginRequest): LoginUser {
        val username: String = loginRequest.username
        val hashedPassword = Password.of(hashing, loginRequest.password).value
        val user = userRepository.findByUsernameAndPassword(username, hashedPassword)
            ?: fail()
        return LoginUser(user.id)
    }

    fun generateAccessToken(loginUser: LoginUser): AccessTokenResponse {
        val authAccessToken: AuthAccessToken = tokenCreator.createAuthToken(loginUser.id)
        return AccessTokenResponse(authAccessToken.accessToken)
    }

    @Transactional
    fun logout(id: Long?) {
        authRepository.deleteAllById(id)
    }

    fun extractUserId(accessToken: String) : Long {
        val userId = tokenCreator.extractPayLoad(accessToken)
        validateExistByUserId(userId)
        return userId
    }

    private fun validateExistByUserId(userId: Long) {
        if (!userRepository.existsByUserId(userId)) {
            throw NotFoundUserException()
        }
    }
}