package com.internal.team.wiki.doc.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.internal.team.wiki.doc.domain.DocEntity
import java.time.LocalDateTime

data class DocDetailResponse(
    val writerName: String,
    val title: String,
    val contents: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    val updatedDateTime: LocalDateTime
) {

    companion object {

        @JvmStatic
        fun of(saveDocEntity: DocEntity): DocDetailResponse {
            return DocDetailResponse(
                writerName = saveDocEntity.user?.nickname?.value ?: "",
                title = saveDocEntity.title.value,
                contents = saveDocEntity.contents.value,
                updatedDateTime = saveDocEntity.updatedDateTime
            )
        }
    }
}
