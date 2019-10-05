package com.pandora.analytics.visit

import org.junit.Test
import reactor.core.publisher.Flux
import java.time.LocalDateTime

internal class VisitTest {

    @Test
    fun foo() {

        val list = mutableListOf(
                Visit(
                "192.168.1.12",
                "http//soemepage.com/someview",
                "http//soemepage.com/someotherview",
                "GET",
                "some remote host",
                "some user agent",
                "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-28T13:42:17.546")
                ),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-29T13:42:17.546")
                ),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-29T11:22:14.784")
                ),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-29T13:42:17.546")
                ),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-04-12T13:42:17.546")),
                Visit(
                "192.168.1.12",
                "http//soemepage.com/someview",
                "http//soemepage.com/someotherview",
                "GET",
                "some remote host",
                "some user agent",
                "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-30T13:42:17.546")
                )
        )

        val a = list
                .map {
                    Visit(it.ipAddress, it.page, it.referrer,
                            it.requestMethod, it.remoteHost, it.userAgent, it.sessionId,
                            it.dateTime
                                    .withHour(0)
                                    .withMinute(0)
                                    .withSecond(0)
                                    .withNano(0)
                    )
                }
                .groupBy {
                    it.dateTime
                }
                .map {
                    VisitByDay(it.key, it.value.size)
                }


        assert(a.size == 4)

        val listOfVisits = Flux.just(
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-28T13:42:17.546")
                ),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-29T13:42:17.546")
                ),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-29T11:22:14.784")
                ),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-29T13:42:17.546")
                ),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-04-12T13:42:17.546")),
                Visit(
                        "192.168.1.12",
                        "http//soemepage.com/someview",
                        "http//soemepage.com/someotherview",
                        "GET",
                        "some remote host",
                        "some user agent",
                        "wejidjidjcijsjdicjs",
                        LocalDateTime.parse("2019-01-30T13:42:17.546")
                )
        )
                .map {
                    Visit(it.ipAddress, it.page, it.referrer,
                            it.requestMethod, it.remoteHost, it.userAgent, it.sessionId,
                            it.dateTime
                                    .withHour(0)
                                    .withMinute(0)
                                    .withSecond(0)
                                    .withNano(0)
                    )
                }
                .groupBy { it.dateTime }
                .map {
                    val a = it.key()
                    val b = it.collectList()


                    val z = 4;
                }

    }
}