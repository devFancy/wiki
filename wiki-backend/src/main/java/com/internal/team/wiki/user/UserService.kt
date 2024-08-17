package com.internal.team.wiki.user

import com.internal.team.wiki.exception.InvalidNicknameException
import com.internal.team.wiki.exception.NotFoundUserException
import com.internal.team.wiki.global.hashing.Hashing
import com.internal.team.wiki.user.domain.Nickname
import com.internal.team.wiki.user.domain.Password
import com.internal.team.wiki.user.domain.UserEntity
import com.internal.team.wiki.user.domain.Username
import com.internal.team.wiki.user.dto.UserDeleteAccountResponse
import com.internal.team.wiki.user.dto.UserSignupRequest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class UserService(
    private val userRepository: UserRepository,
    private val hashing: Hashing,
    private val jdbcTemplate: JdbcTemplate,
) {

    @Transactional
    fun signup(request: UserSignupRequest): Long {
        if (userRepository.existsByNicknameValue(request.nickname)) {
            throw InvalidNicknameException("닉네임이 중복되었습니다.")
        }

        val user = UserEntity(
            username = Username(request.username),
            nickname = Nickname(request.nickname),
            password = Password.of(hashing, request.password),
            deleted = false,
            scheduledDeletionTime = null
        )
        userRepository.save(user)

        return user.id ?: throw NotFoundUserException("User ID should not be null after saving")
    }

    @Transactional
    fun scheduleDeletion(
        loginUser: Long,
    ): UserDeleteAccountResponse {
        val foundUser: UserEntity = userRepository.findByUserId(loginUser)
            .orElseThrow { NotFoundUserException("해당 유저를 찾을 수 없습니다.") }

        val dbTime = getDatabaseTime()
        val nextDay = dbTime.plusDays(1).toLocalDate()
        val sixAM = nextDay.atTime(6, 0) // 새벽 6시로 설정

        foundUser.scheduleDeletion(sixAM)
        foundUser.updateDeleted()

        return UserDeleteAccountResponse(foundUser.id)
    }


    // 데이터베이스의 현재 시간을 가져옴
    private fun getDatabaseTime(): LocalDateTime {
        return jdbcTemplate.queryForObject(
            "SELECT CURRENT_TIMESTAMP",
            LocalDateTime::class.java
        )!! // !!: null이 아님을 확신할 때 사용
    }

    /**
     * 예약된 시간이 지난 회원들을 삭제하는 로직
     * - 현재 시간 이전에 예약된 삭제 시간을 가진 모든 회원들(deleted=true인 회원)을 조회한 뒤
     * - 조회한 각 회원에 대해 회원 엔티티를 삭제
     */
    @Transactional
    fun deleteScheduledUser() {
        val now: LocalDateTime = LocalDateTime.now()
        val usersToDelete: List<UserEntity> = userRepository.findAllByScheduledDeletionTimeBeforeAndDeletedIsTrue(now)

        for (user: UserEntity in usersToDelete) {
            userRepository.delete(user)
        }
    }
}