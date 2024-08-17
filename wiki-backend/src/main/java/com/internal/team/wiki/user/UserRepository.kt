package com.internal.team.wiki.user

import com.internal.team.wiki.user.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun existsByNicknameValue(nickname: String): Boolean

    @Query(
        "SELECT u FROM UserEntity u" +
                " WHERE u.username.value = :username AND u.password.value = :password"
    )
    fun findByUsernameAndPassword(
        @Param("username") username: String,
        @Param("password") password: String
    ): UserEntity?

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE u.id = :userId")
    fun existsByUserId(@Param("userId") userId: Long): Boolean

    @Query("SELECT u FROM UserEntity u WHERE u.id = :userId")
    fun findByUserId(@Param("userId") userId: Long?): Optional<UserEntity>

    fun findAllByScheduledDeletionTimeBeforeAndDeletedIsTrue(now: LocalDateTime): List<UserEntity>
}