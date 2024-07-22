package com.internal.team.wiki.dto.response;

import com.internal.team.wiki.domain.entity.Doc;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DocsResponse {

    private final List<DocResponse> responses;

    public DocsResponse(final List<DocResponse> responses) {
        this.responses = responses;
    }

    public static DocsResponse of(final List<Doc> docs) {
        List<DocResponse> responses = docs.stream()
                .map(DocResponse::from)
                .collect(Collectors.toList());
        return new DocsResponse(responses);
    }
}
