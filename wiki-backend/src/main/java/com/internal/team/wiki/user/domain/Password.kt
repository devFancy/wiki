package com.internal.team.wiki.user.domain

import com.internal.team.wiki.global.hashing.Hashing
import com.internal.team.wiki.exception.InvalidPasswordFormatException
import java.util.regex.Pattern
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Password (

    @Column(name = "password")
    var value: String
) {

    companion object {
        val PATTERN: Pattern = Pattern.compile("[A-Za-z0-9]{4,20}")

        fun of(hashing: Hashing, password: String): Password {
            validateStatic(password)
            return Password(hashing.generateSHA256Hash(password))
        }

        private fun validateStatic(value: String) {
            if (value.isBlank()) {
                throw InvalidPasswordFormatException("비밀번호를 입력해 주세요.")
            }

            if (!PATTERN.matcher(value).matches()) {
                throw InvalidPasswordFormatException("비밀번호는 영문자, 숫자를 포함한 4자에서 20자 사이여야 합니다.")
            }
        }
    }
}