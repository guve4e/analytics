package com.pandora.analytics.Visit

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux

@RestController
@RequestMapping("/visits")
class VisitController(private val visitRepository: VisitRepository) {

    @GetMapping
    fun getAllVisits(): Flux<Visit> =
            visitRepository.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun makeAVisit(@RequestBody visit: Visit) =
            visitRepository.save(visit)

}