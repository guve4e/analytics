package com.pandora.analytics.Visit

import org.springframework.data.mongodb.repository.ReactiveMongoRepository


interface VisitRepository : ReactiveMongoRepository<Visit, String>