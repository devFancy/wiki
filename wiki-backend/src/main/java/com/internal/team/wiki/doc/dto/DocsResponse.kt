package com.internal.team.wiki.doc.dto

import com.internal.team.wiki.doc.domain.DocEntity

data class DocsResponse(
    val responses: List<DocResponse>
) {
    companion object {
        @JvmStatic
        fun of(docEntities: List<DocEntity>): DocsResponse {
            val responses = docEntities.map {
                DocResponse.from(it)
            }
            return DocsResponse(responses)
        }
    }
}