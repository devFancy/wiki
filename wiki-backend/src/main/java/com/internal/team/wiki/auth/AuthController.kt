package com.internal.team.wiki.auth

import com.internal.team.wiki.auth.dto.AccessTokenResponse
import com.internal.team.wiki.auth.dto.LoginRequest
import com.internal.team.wiki.auth.dto.LoginUser
import com.internal.team.wiki.auth.service.AuthService
import com.internal.team.wiki.global.api.ApiResultResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController (
    private val authService: AuthService,
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest) : ResponseEntity<ApiResultResponse<AccessTokenResponse>> {
        val loginUser: LoginUser = authService.login(loginRequest)
        val response: AccessTokenResponse = authService.generateAccessToken(loginUser)
        val apiResponse = ApiResultResponse.success(response, "auths")
        return ResponseEntity(apiResponse, HttpStatus.OK)
    }

    @GetMapping("/logout")
    fun logout(@RequestBody loginUser: LoginUser) : ResponseEntity<ApiResultResponse<Void>> {
        authService.logout(loginUser.id)
        val response = ApiResultResponse.successVoid("Logout successful")
        return ResponseEntity(response, HttpStatus.NO_CONTENT)
    }
}