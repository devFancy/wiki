package com.internal.team.wiki.user

import com.internal.team.wiki.auth.dto.LoginUser
import com.internal.team.wiki.global.api.ApiResultResponse
import com.internal.team.wiki.global.authentication.AuthenticationPrincipal
import com.internal.team.wiki.user.dto.UserDeleteAccountResponse
import com.internal.team.wiki.user.dto.UserSignupRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (
    private val userService: UserService,
) {

    @PostMapping("/api/v1/users/signup")
    fun signup(
        @RequestBody signupRequest: UserSignupRequest
    ): ResponseEntity<ApiResultResponse<Long>> {
        val userId = userService.signup(signupRequest)
        val apiResponse = ApiResultResponse.success(userId, "users")
        return ResponseEntity(apiResponse, HttpStatus.CREATED)
    }

    @DeleteMapping("/api/v1/users")
    fun deleteAccount(
        @AuthenticationPrincipal loginUser: LoginUser) : ResponseEntity<ApiResultResponse<UserDeleteAccountResponse>> {
        val response : UserDeleteAccountResponse = userService.scheduleDeletion(loginUser.id)
        val apiResponse = ApiResultResponse.success(response, "users")
        return ResponseEntity(apiResponse, HttpStatus.NO_CONTENT)
    }
}