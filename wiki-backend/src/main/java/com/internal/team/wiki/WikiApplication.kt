package com.internal.team.wiki

import com.internal.team.wiki.global.properties.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class WikiApplication

fun main(args: Array<String>) {
    runApplication<WikiApplication>(*args)
}