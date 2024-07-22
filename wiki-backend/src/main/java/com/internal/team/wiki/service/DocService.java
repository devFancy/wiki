package com.internal.team.wiki.service;


import com.internal.team.wiki.domain.entity.Doc;
import com.internal.team.wiki.dto.request.DocCreateRequest;
import com.internal.team.wiki.dto.request.DocUpdateRequest;
import com.internal.team.wiki.dto.response.DocDetailResponse;
import com.internal.team.wiki.dto.response.DocsResponse;
import com.internal.team.wiki.exception.NotFoundDocException;
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
                .orElseThrow(() -> new NotFoundDocException());
        return DocDetailResponse.of(doc);
    }

    public DocsResponse findAll() {
        List<Doc> docs = docRepository.findAllByOrderByCreatedDateTimeDesc();
        return DocsResponse.of(docs);
    }

    @Transactional
    public DocDetailResponse update(final Long docId, final DocUpdateRequest request) {
        Doc doc = findDoc(docId);
        doc.change(request.getTitle(), request.getContents());
        return DocDetailResponse.of(doc);
    }

    private Doc findDoc(final Long docId) {
        List<Doc> docs = docRepository.findByDocsId(docId);
        if(docs.isEmpty()) {
            throw new NotFoundDocException();
        }
        return docs.get(0);
    }

    @Transactional
    public void delete(final Long docId) {
        Doc doc = findDoc(docId);
        docRepository.delete(doc);
    }
}
