package com.internal.team.wiki.doc;

import com.internal.team.wiki.doc.dto.request.DocCreateRequest;
import com.internal.team.wiki.doc.dto.request.DocUpdateRequest;
import com.internal.team.wiki.doc.dto.response.DocDetailResponse;
import com.internal.team.wiki.doc.dto.response.DocsResponse;
import com.internal.team.wiki.global.api.ApiResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DocController {

    private final DocService docService;

    public DocController(final DocService docService) {
        this.docService = docService;
    }

    @PostMapping(value = "/api/v1/docs")
    public ResponseEntity<ApiResultResponse<DocDetailResponse>> create(@Valid @RequestBody final DocCreateRequest request) {
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

    @PatchMapping(value = "/api/v1/docs/{id}")
    public ResponseEntity<ApiResultResponse<DocDetailResponse>> update(@PathVariable(name = "id") final Long docId,
                                                                       @Valid @RequestBody final DocUpdateRequest request) {
        DocDetailResponse response = docService.update(docId, request);
        ApiResultResponse<DocDetailResponse> apiResultResponse = ApiResultResponse.success(response, "docs");
        return new ResponseEntity<>(apiResultResponse, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/api/v1/docs/{id}")
    public ResponseEntity<ApiResultResponse<Void>> delete(@PathVariable(name = "id") final Long docId) {

        docService.delete(docId);
        ApiResultResponse<Void> apiResultResponse = ApiResultResponse.successVoid("docs");
        return new ResponseEntity<>(apiResultResponse, HttpStatus.NO_CONTENT);
    }
}
