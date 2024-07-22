package com.internal.team.wiki.controller;

import com.internal.team.wiki.dto.DocCreateRequest;
import com.internal.team.wiki.dto.DocDetailResponse;
import com.internal.team.wiki.dto.DocsResponse;
import com.internal.team.wiki.global.api.ApiResultResponse;
import com.internal.team.wiki.service.DocService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocController {

    private final DocService docService;

    public DocController(final DocService docService) {
        this.docService = docService;
    }

    @PostMapping(value = "/api/v1/docs")
    public ResponseEntity<ApiResultResponse<DocDetailResponse>> create(@RequestBody final DocCreateRequest request) {
        DocDetailResponse response = docService.create(request);
        ApiResultResponse<DocDetailResponse> apiResultResponse = ApiResultResponse.success(response, "docs");
        return new ResponseEntity<>(apiResultResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/v1/docs/{id}")
    public ResponseEntity<ApiResultResponse<DocDetailResponse>> findOne(@PathVariable(name = "id") final Long docId) {
        DocDetailResponse response = docService.findOne(docId);
        ApiResultResponse<DocDetailResponse> apiResultResponse = ApiResultResponse.success(response, "docs");
        return new ResponseEntity<>(apiResultResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/docs")
    public ResponseEntity<ApiResultResponse<DocsResponse>> findAll() {
        DocsResponse response = docService.findAll();
        ApiResultResponse<DocsResponse> apiResultResponse = ApiResultResponse.success(response, "docs");
        return new ResponseEntity<>(apiResultResponse, HttpStatus.OK);
    }
}
