package com.internal.team.wiki.service;


import com.internal.team.wiki.domain.entity.Docs;
import com.internal.team.wiki.dto.DocsCreateRequest;
import com.internal.team.wiki.dto.DocsDetailResponse;
import com.internal.team.wiki.repository.DocsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class DocsService {

    private final DocsRepository docsRepository;

    public DocsService(final DocsRepository docsRepository) {
        this.docsRepository = docsRepository;
    }

    public DocsDetailResponse create(final DocsCreateRequest request) {
        Docs saveDocs = request.toEntity(request);
        docsRepository.save(saveDocs);
        return DocsDetailResponse.of(saveDocs);
    }
}
