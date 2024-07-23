package com.internal.team.wiki.dto.doc.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internal.team.wiki.domain.doc.DocEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocDetailResponse {

    private final String writerName;

    private final String title;

    private final String contents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime updatedDateTime;

    @Builder
    public DocDetailResponse(final String writerName, final String title, final String contents, final LocalDateTime updatedDateTime) {
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
        this.updatedDateTime = updatedDateTime;
    }

    public static DocDetailResponse of(final DocEntity saveDocEntity) {
        return DocDetailResponse.builder()
                .writerName(saveDocEntity.getWriterName())
                .title(saveDocEntity.getTitle().getValue())
                .contents(saveDocEntity.getContents().getValue())
                .updatedDateTime(saveDocEntity.getUpdatedDateTime())
                .build();
    }
}
