package com.b3backoffice.domain.auth

data class ReissueAccessTokenRequest(
    val refreshToken: String,
)
