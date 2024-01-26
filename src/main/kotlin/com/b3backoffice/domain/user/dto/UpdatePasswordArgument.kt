package com.b3backoffice.domain.user.dto

import jakarta.validation.constraints.Pattern

data class UpdatePasswordArgument(
    val previousPassword: String,
    @field:Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*]{8,15}$", message = "비밀번호는 8~15자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
    val newPassword: String
)
