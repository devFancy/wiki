package com.internal.team.wiki.auth

import com.internal.team.wiki.auth.domain.AuthEntityToken
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<AuthEntityToken, Long> {
}