package com.internal.team.wiki.dto.doc.response;

import com.internal.team.wiki.domain.doc.DocEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DocResponse {

    private final String title;

    private final String contents;

    @Builder
    public DocResponse(final String title, final String contents) {
        this.title = title;
        this.contents = contents;
    }

    public static DocResponse from(final DocEntity docEntity) {
        return DocResponse.builder()
                .title(docEntity.getTitle().getValue())
                .contents(docEntity.getContents().getValue())
                .build();
    }
}
