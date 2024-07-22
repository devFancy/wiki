package com.internal.team.wiki.repository;

import com.internal.team.wiki.domain.entity.Docs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocsRepository extends JpaRepository<Docs, Long> {

    @Query("SELECT d FROM Docs d WHERE d.id = :docId")
    Docs findByDocsId(@Param("docId") final Long docId);

    @Query(value = "SELECT d FROM Docs d ORDER BY d.createdDateTime DESC", nativeQuery = false)
    List<Docs> findAllByOrderByCreatedDateTimeDesc();
}
