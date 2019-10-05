package com.pandora.analytics.stats

import com.pandora.analytics.visit.Stats
import org.springframework.data.mongodb.repository.ReactiveMongoRepository


interface StatsRepository : ReactiveMongoRepository<Stats, String>