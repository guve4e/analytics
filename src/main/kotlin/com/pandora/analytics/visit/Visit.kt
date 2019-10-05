package com.pandora.analytics.visit

import reactor.core.publisher.Mono
import java.time.LocalDateTime


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

data class VisitByDay(val date: LocalDateTime?, val numOfVisits: Int)