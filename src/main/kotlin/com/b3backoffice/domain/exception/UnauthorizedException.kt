package com.b3backoffice.domain.exception

data class UnauthorizedException(
    override val message: String? = "You are not authorized to call this API"
):RuntimeException()
