package com.pandora.analytics.Visit

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux

@Controller
class VisitController(private val visitRepository: VisitRepository) {

//    @GetMapping("/visits")
//    fun getAllVisits(): Flux<Visit> =
//            visitRepository.findAll()

    @PostMapping("/visits")
    @ResponseStatus(HttpStatus.CREATED)
    fun makeAVisit(visit: Visit) =
            visitRepository.save(visit)

}

class HttpServletResponse(public val a: Integer, public val b: Integer)