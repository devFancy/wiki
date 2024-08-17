package com.internal.team.wiki.doc

import com.internal.team.wiki.auth.dto.LoginUser
import com.internal.team.wiki.doc.dto.DocCreateRequest
import com.internal.team.wiki.doc.dto.DocDetailResponse
import com.internal.team.wiki.doc.dto.DocUpdateRequest
import com.internal.team.wiki.doc.dto.DocsResponse
import com.internal.team.wiki.global.api.ApiResultResponse
import com.internal.team.wiki.global.authentication.AuthenticationPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DocController(
    private val docService: DocService
) {

    @PostMapping("/api/v1/docs")
    fun create(
        @AuthenticationPrincipal loginUser: LoginUser,
        @RequestBody request: DocCreateRequest
    ): ResponseEntity<ApiResultResponse<DocDetailResponse>> {
        val response = docService.create(loginUser.id, request)
        val apiResultResponse = ApiResultResponse.success(response, "docs")
        return ResponseEntity(apiResultResponse, HttpStatus.CREATED)
    }

    @GetMapping("/api/v1/docs/{id}")
    fun findOne(
        @AuthenticationPrincipal loginUser: LoginUser,
        @PathVariable(name = "id") docId: Long
    ): ResponseEntity<ApiResultResponse<DocDetailResponse>> {
        val response = docService.findOne(loginUser.id, docId)
        val apiResultResponse = ApiResultResponse.success(response, "docs")
        return ResponseEntity(apiResultResponse, HttpStatus.OK)
    }

    @GetMapping("/api/v1/docs")
    fun findAll(): ResponseEntity<ApiResultResponse<DocsResponse>> {
        val response = docService.findAll()
        val apiResultResponse = ApiResultResponse.success(response, "docs")
        return ResponseEntity(apiResultResponse, HttpStatus.OK)
    }

    @PatchMapping("/api/v1/docs/{id}")
    fun update(
        @AuthenticationPrincipal loginUser: LoginUser,
        @PathVariable(name = "id") docId: Long,
        @RequestBody request: DocUpdateRequest
    ): ResponseEntity<ApiResultResponse<DocDetailResponse>> {
        val response = docService.update(loginUser.id, docId, request)
        val apiResultResponse = ApiResultResponse.success(response, "docs")
        return ResponseEntity(apiResultResponse, HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/api/v1/docs/{id}")
    fun delete(
        @AuthenticationPrincipal loginUser: LoginUser,
        @PathVariable(name = "id") docId: Long
    ): ResponseEntity<ApiResultResponse<Void>> {
        docService.delete(loginUser.id, docId)
        val apiResultResponse = ApiResultResponse.successVoid("docs")
        return ResponseEntity(apiResultResponse, HttpStatus.NO_CONTENT)
    }
}
