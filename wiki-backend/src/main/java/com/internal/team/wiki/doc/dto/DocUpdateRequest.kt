package com.internal.team.wiki.doc.dto

import javax.validation.constraints.NotBlank

data class DocUpdateRequest(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val contents: String
)
