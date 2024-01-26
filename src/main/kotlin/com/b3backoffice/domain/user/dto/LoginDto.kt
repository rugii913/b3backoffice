package com.b3backoffice.domain.user.dto

data class LoginDto(
    val accessToken: String,
    val refreshToken: String,
)
