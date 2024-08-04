package com.internal.team.wiki.user.domain

import com.internal.team.wiki.user.domain.hashing.HashingI
import com.internal.team.wiki.user.exception.InvalidPasswordFormatException
import java.util.regex.Pattern
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Password (

    @Column(name = "password")
    var value: String
) {

    init{
        validate(value)
    }

    fun of(hashing: HashingI, password: String): Password {
        validate(password)
        return Password(hashing.generateSHA256Hash(password))
    }

    private fun validate(value: String) {
        if(value.isBlank() || !PATTERN.matcher(value).matches()) {
            throw InvalidPasswordFormatException("비밀번호를 입력해 주세요.")
        }
    }

    companion object {
        val PATTERN: Pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$")
    }
}