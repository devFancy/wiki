package com.internal.team.wiki.controller;

import com.internal.team.wiki.dto.DocsCreateRequest;
import com.internal.team.wiki.dto.DocsDetailResponse;
import com.internal.team.wiki.dto.DocsResponse;
import com.internal.team.wiki.global.api.ApiResultResponse;
import com.internal.team.wiki.service.DocsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/api/v1/docs/{id}")
    public ResponseEntity<ApiResultResponse<DocsDetailResponse>> findOne(@PathVariable(name = "id") final Long docId) {
        DocsDetailResponse response = docsService.findOne(docId);
        ApiResultResponse<DocsDetailResponse> apiResultResponse = ApiResultResponse.success(response, "docs");
        return new ResponseEntity<>(apiResultResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/docs")
    public ResponseEntity<ApiResultResponse<DocsResponse>> findAll() {
        DocsResponse response = docsService.findAll();
        ApiResultResponse<DocsResponse> apiResultResponse = ApiResultResponse.success(response, "docs");
        return new ResponseEntity<>(apiResultResponse, HttpStatus.OK);
    }
}
