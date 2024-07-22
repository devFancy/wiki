package com.internal.team.wiki.dto;

import com.internal.team.wiki.domain.entity.Doc;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocCreateRequest {

    private String writerName;

    private String title;

    private String contents;

    @Builder
    public DocCreateRequest(final String writerName, final String title, final String contents) {
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }

    public Doc toEntity(final DocCreateRequest request) {
        return Doc.builder()
                .writerName(request.getWriterName())
                .title(request.getTitle())
                .contents(request.getContents())
                .build();

    }
}
