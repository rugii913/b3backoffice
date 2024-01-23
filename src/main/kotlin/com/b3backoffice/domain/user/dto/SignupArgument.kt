package com.b3backoffice.domain.user.dto

data class SignupArgument(
        val username: String,
        val password: String,
        val email: String,
        val realName: String,
        val nickname: String,
        val role: String,
        val introduction: String
)
