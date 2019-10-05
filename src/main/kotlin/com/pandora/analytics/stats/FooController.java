package com.pandora.analytics.stats;

import com.pandora.analytics.visit.Stats;
import com.pandora.analytics.visit.Visit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/record")
public class FooController {
    private StatsRepository statsRepository;

    public FooController(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;

    }

    @PostMapping
    public Mono<ResponseEntity<Stats>> updateProduct(@RequestBody Visit visit) {
        return statsRepository.findById("1")
                .flatMap(s -> {
                    s.getVisits().add(visit);
                    return statsRepository.save(s);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
