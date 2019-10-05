package com.pandora.analytics.stats
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import com.pandora.analytics.visit.Stats as Stats1

@RestController
@RequestMapping("/stats")
class StatsController(private val statsRepository: StatsRepository) {

    @GetMapping
    fun getStats(): Flux<Stats1> =
            statsRepository.findAll()

    @PostMapping("/post")
    fun makeStatObject(): Mono<Stats1> =
            statsRepository.save(Stats1("1"))

    @PostMapping
    fun addVisit(@RequestBody visit: Visit): Mono<ResponseEntity<Stats1>> =
        statsRepository.findById("1")
                .flatMap { s ->
                    s.visits.add(visit)
                    statsRepository.save(s)
                }
                .map { updateProduct -> ok(updateProduct) }
                .defaultIfEmpty(ResponseEntity.notFound().build())

}