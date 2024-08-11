package com.internal.team.wiki.doc

import com.internal.team.wiki.doc.domain.DocEntity
import com.internal.team.wiki.doc.dto.DocCreateRequest
import com.internal.team.wiki.doc.dto.DocDetailResponse
import com.internal.team.wiki.doc.dto.DocUpdateRequest
import com.internal.team.wiki.doc.dto.DocsResponse
import com.internal.team.wiki.exception.AuthorizationException
import com.internal.team.wiki.exception.NotFoundDocException
import com.internal.team.wiki.exception.NotFoundUserException
import com.internal.team.wiki.user.UserRepository
import com.internal.team.wiki.user.domain.UserEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DocService(
    private val docRepository: DocRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun create(userId: Long, request: DocCreateRequest): DocDetailResponse {
        validateUser(userId)
        val user = getUserById(userId)

        val saveDocEntity = request.toEntity(user, request)
        docRepository.save(saveDocEntity)
        return DocDetailResponse.of(saveDocEntity)
    }

    fun findOne(userId: Long, docId: Long): DocDetailResponse {
        getUserById(userId)

        val doc = docRepository.findById(docId).get()
        return DocDetailResponse.of(doc)
    }

    fun findAll(): DocsResponse {
        val docEntities = docRepository.findAllByOrderByCreatedDateTimeDesc()
        return DocsResponse.of(docEntities)
    }

    @Transactional
    fun update(userId: Long, docId: Long, request: DocUpdateRequest): DocDetailResponse {
        getUserById(userId)

        val docEntity = findDoc(docId)
        docEntity.change(request.title, request.contents)
        return DocDetailResponse.of(docEntity)
    }

    @Transactional
    fun delete(userId: Long, docId: Long) {
        getUserById(userId)
        val doc = findDoc(docId)

        validateDocByUser(userId, doc)
        docRepository.delete(doc)
    }

    private fun validateUser(userId: Long) {
        if (!existUser(userId)) {
            throw NotFoundUserException("회원 가입해야 글을 작성하실 수 있습니다.")
        }
    }

    private fun existUser(userId: Long): Boolean {
        return userRepository.findByUserId(userId).isPresent
    }

    private fun findDoc(docId: Long): DocEntity {
        val docEntities = docRepository.findByDocsId(docId)
        if (docEntities.isEmpty()) {
            throw NotFoundDocException()
        }
        return docEntities[0]
    }

    private fun getUserById(userId: Long): UserEntity {
        return userRepository.findByUserId(userId)
            .orElseThrow { NotFoundUserException("유저가 존재하지 않습니다.") }
    }

    private fun validateDocByUser(userId: Long, doc: DocEntity) {
        if (!doc.isUser(userId)) {
            throw AuthorizationException()
        }
    }
}
