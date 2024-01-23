package com.b3backoffice.domain.user.service

import com.b3backoffice.domain.user.dto.SignupArgument
import com.b3backoffice.domain.user.dto.UserDto

interface UserService {
    fun signup(request: SignupArgument): UserDto
}