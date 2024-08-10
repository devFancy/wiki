package com.internal.team.wiki.user.domain

import com.internal.team.wiki.exception.InvalidUsernameException
import java.util.regex.Pattern
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Username (

    @Column(name = "username")
    var value: String
) {
    init {
        validate(value)
    }

    private fun validate(value: String) {
        if(value.isBlank()|| !PATTERN.matcher(value).matches()) {
            throw InvalidUsernameException("유효하지 않은 사용자 이름입니다.")
        }
    }

    companion object {
        val PATTERN: Pattern = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z0-9]{4,16}$")
    }

}