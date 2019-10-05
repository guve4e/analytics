package com.pandora.analytics

import com.pandora.analytics.stats.StatsRepository
import com.pandora.analytics.visit.Stats
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Mono
import java.util.function.Consumer


@SpringBootApplication
class AnalyticsApplication

    fun main(args: Array<String>) {
        val app = SpringApplication(AnalyticsApplication::class.java)
        app.webApplicationType = WebApplicationType.REACTIVE
        app.run(*args)
    }

