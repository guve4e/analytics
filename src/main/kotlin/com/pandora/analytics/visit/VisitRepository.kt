package com.pandora.analytics.visit

import com.pandora.analytics.stats.Visit
import org.springframework.data.mongodb.repository.ReactiveMongoRepository


interface VisitRepository : ReactiveMongoRepository<Visit, String>