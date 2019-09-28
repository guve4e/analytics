package com.pandora.analytics.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.netty.util.internal.SocketUtils.accept
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.nio.file.attribute.UserPrincipal
import java.util.HashSet
import java.util.LinkedHashSet

@Component
class ReactiveAuthenticationManagerImpl(private val webClient: WebClient.Builder, private val objectMapper: ObjectMapper) : ReactiveAuthenticationManager {

    @Value("\${authorizationServerUrl}")
    private val authorizationServerUrl: String? = null
    private val authoritiesExtractor = FixedAuthoritiesExtractor()

    private fun extractAuthentication(map: Map<String, Any>): OAuth2Authentication {
        val token = UsernamePasswordAuthenticationToken(getPrincipal(map),
                "N/A", authoritiesExtractor.extractAuthorities(map))
        token.details = map
        return OAuth2Authentication(getRequest(map), token)
    }

    private fun getPrincipal(map: Map<String, Any>): Any? {
        if (map.containsKey("principal")) {
            return try {
                //that is the case for user authentication
                objectMapper.convertValue(map["principal"], UserPrincipal::class.java)
            } catch (ex: IllegalArgumentException) {
                //that is the case for client authentication
                objectMapper.convertValue(map["principal"], String::class.java)
            }
        }
        return null
    }

    private fun getRequest(map: Map<String, Any>): OAuth2Request {
        val clientId = map["client_id"] as String

        val scope = LinkedHashSet(if (map.containsKey("scope"))
            map["scope"] as Collection<String>
        else
            emptySet())

        return OAuth2Request(null, clientId, null, true, HashSet(scope), null, null, null, null)
    }

    private fun authorizeToken(accessToken: String): Mono<Map<String, Any>> {
        return webClient
                .build()
                .post()
                .uri { builder ->
                    builder
                            .scheme("http")
                            .host(authorizationServerUrl)
                            .path("oauth/check_token")/*.port(8100)*/
                            .queryParam("token", accessToken)
                            .build()
                }
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $accessToken")
                .exchange()
                .flatMap { response ->
                    return@flatMap if (response.statusCode().is2xxSuccessful) {
                        response.bodyToMono(object : ParameterizedTypeReference<Map<String, Any>>() {

                        })
                    } else {
                        Mono.error(InvalidTokenException("Invalid token: $accessToken"))
                    }
                }
    }

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.just(authentication)
                .cast(BearerTokenAuthenticationToken::class.java)
                .flatMap { it -> authorizeToken(it.token) }
                .flatMap { result -> Mono.just(extractAuthentication(result)) }
    }
}