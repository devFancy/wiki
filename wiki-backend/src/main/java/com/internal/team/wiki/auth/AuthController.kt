package com.internal.team.wiki.auth

import com.internal.team.wiki.auth.dto.AccessTokenResponse
import com.internal.team.wiki.auth.dto.LoginRequest
import com.internal.team.wiki.auth.dto.LoginUser
import com.internal.team.wiki.auth.service.AuthService
import com.internal.team.wiki.global.api.ApiResultResponse
import com.internal.team.wiki.global.authentication.AuthenticationPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<ApiResultResponse<AccessTokenResponse>> {
        val loginUser: LoginUser = authService.login(loginRequest)
        val response: AccessTokenResponse = authService.generateAccessToken(loginUser)
        val apiResponse = ApiResultResponse.success(response, "auths")
        return ResponseEntity(apiResponse, HttpStatus.OK)
    }

    @DeleteMapping("/logout")
    fun logout(@AuthenticationPrincipal loginUser: LoginUser): ResponseEntity<ApiResultResponse<Void>> {
        authService.logout(loginUser.id)
        val response = ApiResultResponse.successVoid("Logout successful")
        return ResponseEntity(response, HttpStatus.NO_CONTENT)
    }
}