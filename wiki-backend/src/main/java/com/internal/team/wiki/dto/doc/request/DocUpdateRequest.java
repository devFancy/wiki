package com.internal.team.wiki.dto.doc.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DocUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @Builder
    public DocUpdateRequest(final String title, final String contents) {
        this.title = title;
        this.contents = contents;
    }
}
