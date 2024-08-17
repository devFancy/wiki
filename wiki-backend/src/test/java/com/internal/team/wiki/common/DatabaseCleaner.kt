package com.internal.team.wiki.common

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.Table

@Component
class DatabaseCleaner (
    private val entityManager: EntityManager,
) {
    private val tableNames: List<String> = entityManager.metamodel.entities
        .map {
            it.javaType
        }
        .mapNotNull {
            it.getAnnotation(Table::class.java)
        }
        .map {
            it.name
        }

    @Transactional
    fun execute() {
        entityManager.flush()
        entityManager.createNativeQuery("SET foreign_key_checks = 0").executeUpdate()

        tableNames.forEach {
            tableNames -> entityManager.createNativeQuery("TRUNCATE TABLE $tableNames").executeUpdate()
        }

        entityManager.createNativeQuery("SET foreign_key_checks = 1").executeUpdate()
    }
}