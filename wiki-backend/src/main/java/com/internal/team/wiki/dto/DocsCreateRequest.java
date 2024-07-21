package com.internal.team.wiki.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internal.team.wiki.domain.entity.Docs;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocsCreateRequest {

    private String writerName;

    private String title;

    private String contents;

    @Builder
    public DocsCreateRequest(final String writerName, final String title, final String contents) {
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }

    public Docs toEntity(final DocsCreateRequest request) {
        return Docs.builder()
                .writerName(request.getWriterName())
                .title(request.getTitle())
                .contents(request.getContents())
                .build();

    }
}
