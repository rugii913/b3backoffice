package com.b3backoffice.domain.auth.service

import com.b3backoffice.domain.auth.dto.ReissueAccessTokenResponse
import com.b3backoffice.domain.auth.exception.InvalidatedTokenException
import com.b3backoffice.infra.security.jwt.JwtPlugin
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenInvalidationService: TokenInvalidationService,
    private val jwtPlugin: JwtPlugin,
) {

    fun reissueTokens(refreshTokenInRequest: String): ReissueAccessTokenResponse {
        if (tokenInvalidationService.isInvalidatedToken(refreshTokenInRequest)) throw InvalidatedTokenException()

        val (reissuedAccessToken, reissuedRefreshToken) = jwtPlugin.validateToken(refreshTokenInRequest)
            .getOrThrow()
            .let {
                Pair(
                    jwtPlugin.generateAccessToken(
                        it.payload.subject,
                        it.payload.get("username", String::class.java),
                        it.payload.get("role", String::class.java),
                    ),
                    jwtPlugin.generateRefreshToken(
                        it.payload.subject,
                        it.payload.get("username", String::class.java),
                        it.payload.get("role", String::class.java),
                    )
                )
            }

        tokenInvalidationService.invalidateToken(refreshTokenInRequest)

        return ReissueAccessTokenResponse(accessToken = reissuedAccessToken, refreshToken = reissuedRefreshToken)
    }
}
