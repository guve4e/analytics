package com.pandora.analytics.visit

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class Stats(@Id private val id: String) {

    var visits: MutableList<Visit> = mutableListOf()
    val numOfVisits: Int
        get() { return visits.size }

//    var numOfVisitsPerDay: List<VisitByDay> =
//        visits
//                .map {
//                    Visit(it.ipAddress, it.page, it.referrer,
//                            it.requestMethod, it.remoteHost, it.userAgent, it.sessionId,
//                            it.dateTime
//                            .withHour(0)
//                            .withMinute(0)
//                            .withSecond(0)
//                            .withNano(0))
//                }
//                .groupBy {
//                    it.dateTime
//                }
//                .map {
//                    VisitByDay(it.key, it.value.size)
//                }
}