package com.pandora.analytics.visit

import com.pandora.analytics.stats.StatsRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.util.*

class ByDay(val date : Date, val numOfVisits: Int)

@RestController
@RequestMapping("/visits")
class VisitController(private val visitRepository: VisitRepository) {

    @GetMapping
    fun getAllVisits(): Flux<Visit> =
            visitRepository.findAll()

    @GetMapping("/byDay")
    fun getVisitsByDay(): Flux<VisitByDay> =
        visitRepository
                .findAll()
                .map {
                    Visit(it.ipAddress, it.page, it.referrer,
                            it.requestMethod, it.remoteHost, it.userAgent, it.sessionId,
                            it.dateTime
                                    .withHour(0)
                                    .withMinute(0)
                                    .withSecond(0)
                                    .withNano(0)
                    )
                }
                .groupBy {
                    it.dateTime
                }
                .map {
                    VisitByDay(it.key(), 1)
                }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun makeAVisit(@RequestBody visit: Visit) =
            visitRepository.save(visit)

}