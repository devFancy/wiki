package com.internal.team.wiki.auth.domain

import com.internal.team.wiki.global.BaseTimeEntity
import com.internal.team.wiki.user.domain.UserEntity
import javax.persistence.*

@Table(name = "auth_tokens")
@Entity
class AuthTokenEntity (

    @OneToOne(fetch = FetchType.LAZY)
    val userEntity: UserEntity,

    @Column(name = "refresh_token")
    val refreshToken: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null

) : BaseTimeEntity() {
}