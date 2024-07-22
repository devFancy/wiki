package com.internal.team.wiki.dto;

import com.internal.team.wiki.domain.entity.Docs;
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

    public static DocResponse from(final Docs doc) {
        return DocResponse.builder()
                .title(doc.getTitle())
                .contents(doc.getContents())
                .build();
    }
}
