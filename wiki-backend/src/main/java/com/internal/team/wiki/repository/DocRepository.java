package com.internal.team.wiki.repository;

import com.internal.team.wiki.domain.doc.DocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocRepository extends JpaRepository<DocEntity, Long> {

    @Query("SELECT d FROM DocEntity d WHERE d.id = :docId")
    List<DocEntity> findByDocsId(@Param("docId") final Long docId);

    @Query(value = "SELECT d FROM DocEntity d ORDER BY d.createdDateTime DESC")
    List<DocEntity> findAllByOrderByCreatedDateTimeDesc();
}
