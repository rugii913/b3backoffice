package com.b3backoffice.domain.user.service

import com.b3backoffice.domain.user.dto.LoginArgument
import com.b3backoffice.domain.user.dto.LoginDto
import com.b3backoffice.domain.user.dto.SignupArgument
import com.b3backoffice.domain.user.dto.UserDto

interface UserService {
    fun signup(request: SignupArgument): UserDto
    fun login(request: LoginArgument): LoginDto
}