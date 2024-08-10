package com.internal.team.wiki.user.domain

import com.internal.team.wiki.global.BaseTimeEntity
import javax.persistence.*

@Table(name ="users")
@Entity
class UserEntity (

    @Embedded
    var username: Username,

    @Embedded
    val nickname: Nickname,

    @Embedded
    var password: Password,

    @Enumerated(EnumType.STRING)
    val roleType: RoleType = RoleType.USER,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseTimeEntity() {
}