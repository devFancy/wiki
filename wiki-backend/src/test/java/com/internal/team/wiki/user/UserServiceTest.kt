package com.internal.team.wiki.user

import com.internal.team.wiki.IntegrationTestSupport
import com.internal.team.wiki.user.domain.*
import com.internal.team.wiki.user.dto.UserDeleteAccountResponse
import com.internal.team.wiki.user.dto.UserSignupRequest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest (
    @Autowired private val userService: UserService,
    @Autowired private val userRepository: UserRepository,
) : IntegrationTestSupport() {

    @AfterEach
    fun tearDown() {
        userRepository.deleteAllInBatch()
    }

    @DisplayName("회원 가입을 한다.")
    @Test
    fun 회원_가입이_정상_동작된다() {
        // given
        val request = UserSignupRequest(
            username = "devfancy",
            nickname = "fancy",
            password = "ag238925"
        )

        // when
        val userId = userService.signup(request)

        // then
        assertNotNull(userId, "회원가입 이후에 UserId는 null이 될 수 없습니다.")
        val savedUser = userRepository.findById(userId)
        assertTrue(savedUser.isPresent, "유저가 DB에 저장되어 있지 않습니다.")
    }

    @DisplayName("회원 탈퇴를 예약한다.")
    @Test
    @WithMockUser(username = "devfancy") // Mock user authentication
    fun 회원_탈퇴가_정상_동작된다() {

        // given
        val request = UserSignupRequest(
            username = "devfancy",
            nickname = "fancy",
            password = "ag238925"
        )
        val userId = userService.signup(request)

        // when
        val response: UserDeleteAccountResponse = userService.scheduleDeletion(userId)

        // then
        assertEquals(userId, response.id)
        val user = userRepository.findByUserId(userId)
        assertTrue(user.get().deleted)
        assertNotNull(user.get().scheduledDeletionTime)
    }
}