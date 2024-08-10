package com.internal.team.wiki.auth

import com.internal.team.wiki.auth.domain.AuthTokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<AuthTokenEntity, Long> {

    fun deleteAllById(authTokenId: Long?)
}