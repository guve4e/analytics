package com.pandora.analytics.stats
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import com.pandora.analytics.visit.Stats as Stats1

@RestController
@RequestMapping("/stats")
class StatsController(private val statsRepository: StatsRepository) {

    @PostMapping("/post")
    fun updateProduct(): Mono<Stats1> {
        return statsRepository.save(Stats1("1"))
    }

    @PostMapping
    fun updateProduct(@RequestBody visit: Visit): Mono<ResponseEntity<com.pandora.analytics.visit.Stats>> {
        return statsRepository.findById("1")
                .flatMap { s ->
                    s.visits.add(visit)
                    statsRepository.save(s)
                }
                .map { updateProduct -> ok(updateProduct) }
                .defaultIfEmpty(ResponseEntity.notFound().build())
    }
}