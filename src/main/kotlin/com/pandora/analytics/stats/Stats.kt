package com.pandora.analytics.visit


class Stats(private val id: String) {

    var visits: MutableList<Visit> = mutableListOf()
    var numOfVisits: Int = visits.size
}