package com.pandora.analytics

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class AnalyticsApplication

fun main(args: Array<String>) {
	val app = SpringApplication(AnalyticsApplication::class.java)
	app.webApplicationType = WebApplicationType.REACTIVE
	app.run(*args)
}
