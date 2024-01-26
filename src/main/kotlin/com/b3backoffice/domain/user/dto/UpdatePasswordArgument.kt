package com.b3backoffice.domain.user.dto

import jakarta.validation.constraints.Pattern

data class UpdatePasswordArgument(
    val previousPassword: String,
    @field:Pattern(
        regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*])[a-zA-Z\\d!@#\$%^&*]{8,15}",
        message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 각각 적어도 하나씩 사용하여 8 ~ 15자로 이루어져야 합니다."
    )
    val newPassword: String
)
