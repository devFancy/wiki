package com.internal.team.wiki.service;


import com.internal.team.wiki.domain.entity.Doc;
import com.internal.team.wiki.dto.DocCreateRequest;
import com.internal.team.wiki.dto.DocDetailResponse;
import com.internal.team.wiki.dto.DocsResponse;
import com.internal.team.wiki.global.error.CustomException;
import com.internal.team.wiki.global.error.ErrorCode;
import com.internal.team.wiki.repository.DocRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class DocService {

    private final DocRepository docRepository;

    public DocService(final DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    @Transactional
    public DocDetailResponse create(final DocCreateRequest request) {
        Doc saveDoc = request.toEntity(request);
        docRepository.save(saveDoc);
        return DocDetailResponse.of(saveDoc);
    }

    public DocDetailResponse findOne(final Long docId) {
        Doc doc = docRepository.findById(docId)
                .orElseThrow(() -> new CustomException(ErrorCode.DOCUMENT_NOT_FOUND, "Document not found"));
        return DocDetailResponse.of(doc);
    }

    public DocsResponse findAll() {
        List<Doc> docs = docRepository.findAllByOrderByCreatedDateTimeDesc();
        return DocsResponse.of(docs);
    }
}
