package com.pandora.analytics.visit

import org.springframework.data.mongodb.repository.ReactiveMongoRepository


interface VisitRepository : ReactiveMongoRepository<Visit, String>