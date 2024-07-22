package com.internal.team.wiki.repository;

import com.internal.team.wiki.domain.entity.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocRepository extends JpaRepository<Doc, Long> {

    @Query("SELECT d FROM Doc d WHERE d.id = :docId")
    Doc findByDocsId(@Param("docId") final Long docId);

    @Query(value = "SELECT d FROM Doc d ORDER BY d.createdDateTime DESC", nativeQuery = false)
    List<Doc> findAllByOrderByCreatedDateTimeDesc();
}
