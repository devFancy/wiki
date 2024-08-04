package com.internal.team.wiki.user.domain

import com.internal.team.wiki.global.BaseEntityTime
import javax.persistence.*

@Table(name ="users")
@Entity
class UserEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Embedded
    var username: Username,

    @Embedded
    val nickname: Nickname,

    @Embedded
    var password: Password,

    @Enumerated
    var roleType: RoleType = RoleType.USER
) : BaseEntityTime() {

    init {
        if (username.value.isBlank()) {
            throw IllegalArgumentException("이름을 비어있을 수 없습니다.")
        }
        if (password.value.isBlank()) {
            throw IllegalArgumentException("이메일은 비어있을 수 없습니다.")
        }
    }
}