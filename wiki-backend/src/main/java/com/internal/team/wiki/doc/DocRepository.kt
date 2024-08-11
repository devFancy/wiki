package com.internal.team.wiki.doc

import com.internal.team.wiki.doc.domain.DocEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DocRepository : JpaRepository<DocEntity, Long> {
    @Query("SELECT d FROM DocEntity d WHERE d.id = :docId")
    fun findByDocsId(@Param("docId") docId: Long): List<DocEntity>

    @Query("SELECT d FROM DocEntity d ORDER BY d.createdDateTime DESC")
    fun findAllByOrderByCreatedDateTimeDesc(): List<DocEntity>
}
