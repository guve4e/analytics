package com.pandora.analytics.visit

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Document
class Visit(
        val ipAddress: String,
        val page: String,
        val referrer: String,
        val requestMethod: String,
        val remoteHost: String,
        val userAgent: String,
        val sessionId: String,
        val dateTime: LocalDateTime = LocalDateTime.now()
)
{
    @Id
    val id: String? = null
}

@Document
class VisitByDay(val date: LocalDateTime?, val numOfVisits: Int) {
    @Id
    val id: String? = null
}



