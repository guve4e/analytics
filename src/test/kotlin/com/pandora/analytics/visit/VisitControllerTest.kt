package com.pandora.analytics.visit

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
internal class VisitControllerTest {

    @Autowired
    private lateinit var repository: VisitRepository

    private lateinit var client: WebTestClient

    @Before
    fun beforeEach() {
        client = WebTestClient
                .bindToController(VisitController(repository))
                .build()
    }

    @Test
    fun testAddAndGetVisit() {

        var visit = Visit(
                "192.168.1.12",
                "http//soemepage.com/someview",
                "http//soemepage.com/someotherview",
                "GET",
                "some remote host",
                "some user agent",
                "wejidjidjcijsjdicjs")

        client
                .post()
                .uri("/visits")
                .syncBody(visit)
                .exchange()
                .expectStatus()
                .isCreated

        client
                .get()
                .uri("/visits")
                .exchange()
                .expectBodyList<Visit>()
                .returnResult()
                .responseBody
    }
}