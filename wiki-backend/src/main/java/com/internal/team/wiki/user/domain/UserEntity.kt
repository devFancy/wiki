package com.internal.team.wiki.user.domain

import com.internal.team.wiki.global.BaseEntityTime
import com.internal.team.wiki.user.exception.InvalidNicknameException
import com.internal.team.wiki.user.exception.InvalidPasswordFormatException
import com.internal.team.wiki.user.exception.InvalidUsernameException
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
    val roleType: RoleType = RoleType.USER
) : BaseEntityTime() {

    init {
        if (username.value.isBlank()) {
            throw InvalidUsernameException("이름은 비어있을 수 없습니다.")
        }
        if (nickname.value.isBlank()) {
            throw InvalidNicknameException("닉네임은 비어있을 수 없습니다.")
        }
        if (password.value.isBlank()) {
            throw InvalidPasswordFormatException("이메일은 비어있을 수 없습니다.")
        }
    }
}