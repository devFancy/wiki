package com.internal.team.wiki.doc.dto.request;

import com.internal.team.wiki.doc.domain.DocEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocCreateRequest {

    @NotBlank
    private String writerName;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @Builder
    public DocCreateRequest(final String writerName, final String title, final String contents) {
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }

    public DocEntity toEntity(final DocCreateRequest request) {
        return DocEntity.builder()
                .writerName(request.getWriterName())
                .title(request.getTitle())
                .contents(request.getContents())
                .build();

    }
}
