package com.b3backoffice.domain.user.dto

import jakarta.validation.constraints.Pattern

data class SignupArgument(
        @field:Pattern(regexp = "^[a-z0-9]{4,10}$", message = "username 는 4~10자의 영문 소문자, 숫자로 이루어져야 합니다.")
        val username: String,
        @field:Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*]{8,15}$", message = "비밀번호는 8~15자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
        val password: String,
        val email: String,
        val realName: String,
        val nickname: String,
        val role: String,
        val introduction: String
)
