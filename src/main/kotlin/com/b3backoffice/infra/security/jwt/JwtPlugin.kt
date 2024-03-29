package com.b3backoffice.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
        @Value("\${auth.jwt.issuer}") private val issuer: String,
        @Value("\${auth.jwt.secret}") private val secret: String,
        @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
        @Value("\${auth.jwt.refreshTokenExpirationHour}") private val refreshTokenExpirationHour: Long,
) {

    fun validateToken(jwt: String): Result<Jws<Claims>>{
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }
    fun generateAccessToken(subject: String, username: String, role: String): String{
        return generateToken(subject, username, role, Duration.ofHours(accessTokenExpirationHour))
    }

    fun generateRefreshToken(subject: String, username: String, role: String): String{
        return generateToken(subject, username, role, Duration.ofHours(refreshTokenExpirationHour))
    }

    private fun generateToken(subject: String, username: String, role: String, expirationPeriod: Duration): String{
        val claims: Claims = Jwts.claims()
                .add(mapOf("username" to username, "role" to role))
                .build()

        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        return Jwts.builder()
                .subject(subject)
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expirationPeriod)))
                .claims(claims)
                .signWith(key)
                .compact()
    }

    /*
    // 생각할 수 있는 가장 단순한 방식으로 refresh token에도 subject, claims를 넣어둠
    fun generateRefreshToken(): String {
        val now = Instant.now()
        val expirationPeriod = Duration.ofHours(refreshTokenExpirationHour)
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .signWith(key)
            .compact()
    }
    */
}
