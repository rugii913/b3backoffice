package com.b3backoffice.domain.auth.service

import com.b3backoffice.domain.auth.model.InvalidatedToken
import com.b3backoffice.domain.auth.repository.InvalidatedTokenRepository
import org.springframework.stereotype.Service

@Service
class TokenInvalidationService(
    private val invalidatedTokenRepository: InvalidatedTokenRepository,
) {

    fun expireToken(token: String): Unit {
        invalidatedTokenRepository.save(InvalidatedToken(token))
    }

    fun isInvalidatedToken(token: String): Boolean {
        return invalidatedTokenRepository.existsByToken(token)
    }
}
