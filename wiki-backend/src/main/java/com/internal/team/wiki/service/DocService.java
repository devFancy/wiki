package com.internal.team.wiki.service;


import com.internal.team.wiki.domain.doc.DocEntity;
import com.internal.team.wiki.dto.doc.request.DocCreateRequest;
import com.internal.team.wiki.dto.doc.request.DocUpdateRequest;
import com.internal.team.wiki.dto.doc.response.DocDetailResponse;
import com.internal.team.wiki.dto.doc.response.DocsResponse;
import com.internal.team.wiki.exception.doc.NotFoundDocException;
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
        DocEntity saveDocEntity = request.toEntity(request);
        docRepository.save(saveDocEntity);
        return DocDetailResponse.of(saveDocEntity);
    }

    public DocDetailResponse findOne(final Long docId) {
        DocEntity docEntity = docRepository.findById(docId)
                .orElseThrow(() -> new NotFoundDocException());
        return DocDetailResponse.of(docEntity);
    }

    public DocsResponse findAll() {
        List<DocEntity> docEntities = docRepository.findAllByOrderByCreatedDateTimeDesc();
        return DocsResponse.of(docEntities);
    }

    @Transactional
    public DocDetailResponse update(final Long docId, final DocUpdateRequest request) {
        DocEntity docEntity = findDoc(docId);
        docEntity.change(request.getTitle(), request.getContents());
        return DocDetailResponse.of(docEntity);
    }

    private DocEntity findDoc(final Long docId) {
        List<DocEntity> docEntities = docRepository.findByDocsId(docId);
        if(docEntities.isEmpty()) {
            throw new NotFoundDocException();
        }
        return docEntities.get(0);
    }

    @Transactional
    public void delete(final Long docId) {
        DocEntity docEntity = findDoc(docId);
        docRepository.delete(docEntity);
    }
}
