package com.internal.team.wiki

import com.internal.team.wiki.common.DatabaseCleaner
import com.internal.team.wiki.global.config.JpaAuditingConfig
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@Import(JpaAuditingConfig::class)
@ActiveProfiles("test")
@SpringBootTest
abstract class IntegrationTestSupport {

    @Autowired
    private lateinit var databaseCleaner: DatabaseCleaner

    @BeforeEach
    fun setUp() {
        databaseCleaner.execute()
    }
}