package com.internal.team.wiki.user

import com.internal.team.wiki.user.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun existsByNicknameValue(nickname: String): Boolean
}