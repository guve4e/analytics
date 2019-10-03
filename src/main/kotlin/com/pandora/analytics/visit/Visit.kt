package com.pandora.analytics.visit


data class Visit(
                 val ipAddress: String,
                 val page: String,
                 val referrer: String,
                 val requestMethod: String,
                 val remoteHost: String,
                 val userAgent: String,
                 val sessionId: String
                 )