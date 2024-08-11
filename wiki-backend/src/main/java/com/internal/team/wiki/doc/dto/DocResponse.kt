package com.internal.team.wiki.doc.dto

import com.internal.team.wiki.doc.domain.DocEntity


data class DocResponse(
    val title: String,
    val contents: String
) {
    companion object {
        @JvmStatic
        fun from(docEntity: DocEntity): DocResponse {
            return DocResponse(
                title = docEntity.title.value,
                contents = docEntity.contents.value
            )
        }
    }
}

