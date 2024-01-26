package com.b3backoffice.domain.user.dto

import jakarta.validation.constraints.Pattern

data class SignupArgument(
        @field:Pattern(
                regexp = "[a-z]{1}(?=.*[a-z])(?=.*\\d)[a-z\\d]{3,9}",
                message = "username은 영문 소문자, 숫자를 사용하여 4 ~ 10자로 이루어져야 하며, 첫글자로 숫자는 불가능합니다."
        )
        val username: String,
        @field:Pattern(
                regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*])[a-zA-Z\\d!@#\$%^&*]{8,15}",
                message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 각각 적어도 하나씩 사용하여 8 ~ 15자로 이루어져야 합니다."
        )
        val password: String,
        val email: String,
        val realName: String,
        val nickname: String,
        val role: String,
        val introduction: String
)
