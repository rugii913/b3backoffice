package com.b3backoffice.domain.auth.dto

data class ReissueAccessTokenRequest(
    val refreshToken: String,
)
