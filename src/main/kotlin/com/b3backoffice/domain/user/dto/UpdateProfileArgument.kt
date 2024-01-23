package com.b3backoffice.domain.user.dto

data class UpdateProfileArgument(
    val email:String,
    val nickname:String,
    val introduction:String
)
