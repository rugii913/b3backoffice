package com.b3backoffice.domain.auth.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class InvalidatedToken(
    @Id
    val token: String
)
