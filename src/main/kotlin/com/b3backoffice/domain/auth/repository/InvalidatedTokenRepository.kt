package com.b3backoffice.domain.auth.repository

import com.b3backoffice.domain.auth.model.InvalidatedToken
import org.springframework.data.jpa.repository.JpaRepository

interface InvalidatedTokenRepository :JpaRepository<InvalidatedToken, Long> {

    fun existsByToken(token: String): Boolean
}
