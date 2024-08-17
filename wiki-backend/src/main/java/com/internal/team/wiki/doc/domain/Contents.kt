package com.internal.team.wiki.doc.domain

import com.internal.team.wiki.exception.InvalidContentException
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Lob

@Embeddable
class Contents(

    @Column(name = "contents", nullable = false)
    @Lob
    val value: String,
) {

    init {
        validate(value)
    }

    private fun validate(value: String) {
        require(value.isNotBlank()) {
            throw InvalidContentException()
        }
    }
}