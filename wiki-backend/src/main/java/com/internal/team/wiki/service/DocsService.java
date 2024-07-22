package com.internal.team.wiki.service;


import com.internal.team.wiki.domain.entity.Docs;
import com.internal.team.wiki.dto.DocsCreateRequest;
import com.internal.team.wiki.dto.DocsDetailResponse;
import com.internal.team.wiki.dto.DocsResponse;
import com.internal.team.wiki.global.error.CustomException;
import com.internal.team.wiki.global.error.ErrorCode;
import com.internal.team.wiki.repository.DocsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class DocsService {

    private final DocsRepository docsRepository;

    public DocsService(final DocsRepository docsRepository) {
        this.docsRepository = docsRepository;
    }

    @Transactional
    public DocsDetailResponse create(final DocsCreateRequest request) {
        Docs saveDocs = request.toEntity(request);
        docsRepository.save(saveDocs);
        return DocsDetailResponse.of(saveDocs);
    }

    public DocsDetailResponse findOne(final Long docId) {
        Docs docs = docsRepository.findById(docId)
                .orElseThrow(() -> new CustomException(ErrorCode.DOCUMENT_NOT_FOUND, "Document not found"));
        return DocsDetailResponse.of(docs);
    }

    public DocsResponse findAll() {
        List<Docs> docs = docsRepository.findAllByOrderByCreatedDateTimeDesc();
        return DocsResponse.of(docs);
    }
}
