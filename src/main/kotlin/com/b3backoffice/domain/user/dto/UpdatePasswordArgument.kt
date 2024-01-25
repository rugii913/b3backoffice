package com.b3backoffice.domain.user.dto

data class UpdatePasswordArgument(
    val previousPassword: String,
    val newPassword: String
)
