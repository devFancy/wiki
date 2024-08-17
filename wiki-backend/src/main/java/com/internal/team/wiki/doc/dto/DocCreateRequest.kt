package com.internal.team.wiki.doc.dto

import com.internal.team.wiki.doc.domain.DocEntity
import com.internal.team.wiki.user.domain.UserEntity
import javax.validation.constraints.NotBlank

data class DocCreateRequest(

    @field:NotBlank
    val writerName: String,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val contents: String
) {
    fun toEntity(user: UserEntity, request: DocCreateRequest): DocEntity {
        return DocEntity(
            user = user,
            writerName = user.nickname.value,
            title = request.title,
            contents = request.contents
        )
    }
}
