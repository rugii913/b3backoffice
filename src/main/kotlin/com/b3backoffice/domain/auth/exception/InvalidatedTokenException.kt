package com.b3backoffice.domain.auth.exception

data class InvalidatedTokenException(
    override val message: String = "유효하지 않은 토큰입니다."
) : RuntimeException()
