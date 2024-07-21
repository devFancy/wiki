package com.internal.team.wiki.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internal.team.wiki.domain.entity.Docs;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocsDetailResponse {

    private final String writerName;

    private final String title;

    private final String contents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime updatedDateTime;

    @Builder
    public DocsDetailResponse(final String writerName, final String title, final String contents, final LocalDateTime updatedDateTime) {
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
        this.updatedDateTime = updatedDateTime;
    }

    public static DocsDetailResponse of(final Docs saveDocs) {
        return DocsDetailResponse.builder()
                .writerName(saveDocs.getWriterName())
                .title(saveDocs.getTitle())
                .contents(saveDocs.getContents())
                .updatedDateTime(saveDocs.getUpdatedDateTime())
                .build();
    }
}
