package com.b3backoffice.infra.security.jwt.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class InvalidatedToken(
    @Id
    val token: String
)
