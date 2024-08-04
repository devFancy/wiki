package com.internal.team.wiki.user

import com.internal.team.wiki.global.api.ApiResultResponse
import com.internal.team.wiki.user.dto.UserSignupRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (
    private val userService: UserService,
) {

    @PostMapping("/users/signup")
    fun signup(@RequestBody signupRequest: UserSignupRequest): ResponseEntity<ApiResultResponse<Long>> {
        val userId = userService.signup(signupRequest);
        val apiResponse = ApiResultResponse.success(userId, "users")
        return ResponseEntity(apiResponse, HttpStatus.CREATED);
    }
}