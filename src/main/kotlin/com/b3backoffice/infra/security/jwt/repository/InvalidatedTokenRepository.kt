package com.b3backoffice.infra.security.jwt.repository

import com.b3backoffice.infra.security.jwt.model.InvalidatedToken
import org.springframework.data.jpa.repository.JpaRepository

interface InvalidatedTokenRepository :JpaRepository<InvalidatedToken, Long> {

    fun existsByToken(token: String): Boolean
}
