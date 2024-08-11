package com.internal.team.wiki.user

import com.internal.team.wiki.exception.NotFoundUserException
import com.internal.team.wiki.user.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun existsByNicknameValue(nickname: String): Boolean

    @Query("SELECT u FROM UserEntity u" +
            " WHERE u.username.value = :username AND u.password.value = :password")
    fun findByUsernameAndPassword(
        @Param("username") username: String,
        @Param("password") password: String
    ): UserEntity?

    fun validateExistById(userId: Long) {
        if (!existsById(userId)) {
            throw NotFoundUserException()
        }
    }
}