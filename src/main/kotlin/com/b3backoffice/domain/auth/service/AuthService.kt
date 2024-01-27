package com.b3backoffice.domain.auth.service

import com.b3backoffice.domain.auth.dto.ReissueAccessTokenResponse
import com.b3backoffice.infra.security.jwt.JwtPlugin
import com.b3backoffice.infra.security.jwt.exception.InvalidatedTokenException
import com.b3backoffice.infra.security.jwt.service.TokenInvalidationService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val tokenInvalidationService: TokenInvalidationService,
    private val jwtPlugin: JwtPlugin,
) {

    fun reissueTokens(refreshToken: String): ReissueAccessTokenResponse {
        if (tokenInvalidationService.isInvalidatedToken(refreshToken)) throw InvalidatedTokenException()

        val accessToken = jwtPlugin.validateToken(refreshToken)
            .getOrThrow()
            .let {
                jwtPlugin.generateAccessToken(
                    it.payload.subject,
                    it.payload.get("username", String::class.java),
                    it.payload.get("role", String::class.java),
                )
            }

        return ReissueAccessTokenResponse(accessToken)
    }
}
