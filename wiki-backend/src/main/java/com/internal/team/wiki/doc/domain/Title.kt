package com.internal.team.wiki.doc.domain

import com.internal.team.wiki.exception.InvalidTitleException
import lombok.Getter
import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class Title (

    @Column(name = "title", nullable = false)
    val value: String,
) {
    companion object {
        private const val MAX_TITLE_LENGTH = 30
    }

    init {
        validate(value)
    }

    private fun validate(value: String) {
        require(value.isNotBlank()) {
            throw InvalidTitleException()
        }

        require(value.length <= MAX_TITLE_LENGTH) {
            throw InvalidTitleException()
        }
    }

}