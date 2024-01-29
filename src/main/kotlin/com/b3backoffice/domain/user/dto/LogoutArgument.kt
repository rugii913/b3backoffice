package com.b3backoffice.domain.user.dto

data class LogoutArgument(
    val refreshToken: String? = null, // logout 요청 시 body에 아무 것도 없는 경우에는, tokenInvalidationService.expireToken(refreshToken)을 호출하지 않을 것
)
