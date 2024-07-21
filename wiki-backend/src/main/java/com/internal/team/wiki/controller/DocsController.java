package com.internal.team.wiki.controller;

import com.internal.team.wiki.dto.DocsCreateRequest;
import com.internal.team.wiki.dto.DocsDetailResponse;
import com.internal.team.wiki.global.api.ApiResultResponse;
import com.internal.team.wiki.service.DocsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocsController {

    private final DocsService docsService;

    public DocsController(final DocsService docsService) {
        this.docsService = docsService;
    }

    @PostMapping(value = "/api/v1/docs")
    public ResponseEntity<ApiResultResponse<DocsDetailResponse>> create(@RequestBody final DocsCreateRequest request) {
        DocsDetailResponse response = docsService.create(request);
        ApiResultResponse<DocsDetailResponse> apiResultResponse = ApiResultResponse.success(response, "docs");
        return new ResponseEntity<>(apiResultResponse, HttpStatus.CREATED);
    }

}
