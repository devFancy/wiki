package com.internal.team.wiki.user.domain

import com.internal.team.wiki.user.domain.hashing.HashingI
import com.internal.team.wiki.user.exception.InvalidUsernameException
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

    fun of(hashing: HashingI, value: String): Username {
        validate(value)
        return Username(hashing.generateSHA256Hash(value))
    }

    private fun validate(value: String) {
        if(value.isBlank()|| !PATTERN.matcher(value).matches()) {
            throw InvalidUsernameException()
        }
    }

    companion object {
        val PATTERN: Pattern = Pattern.compile("^[0-9a-zA-Z]{4,16}$")
    }

}