package com.internal.team.wiki.user.domain

import com.internal.team.wiki.user.exception.InvalidNicknameException
import java.util.regex.Pattern
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Nickname (
    @Column(name = "nickname", unique = true)
    val value: String
) {
    init {
        validate(value)
    }

    private fun validate(value: String) {
        if (value.length < MIN_LENGTH || value.length > MAX_LENGTH || PATTERN.matcher(value).matches()
        ) {
            throw InvalidNicknameException()
        }
    }

    companion object {
        val PATTERN: Pattern = Pattern.compile("^[0-9a-zA-Z가-힣]+(?:\\s+[0-9a-zA-Z가-힣]+)*$")
        const val MIN_LENGTH: Int = 1
        const val MAX_LENGTH: Int = 16
    }
}