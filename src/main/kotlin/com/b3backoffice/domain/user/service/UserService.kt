package com.b3backoffice.domain.user.service

import com.b3backoffice.domain.user.dto.*

interface UserService {
    fun signup(request: SignupArgument): UserDto
    fun login(request: LoginArgument): LoginDto
    fun updateProfile(userId:Long, request:UpdateProfileArgument)
    fun updatePassword(userId: Long, request: UpdatePasswordArgument)
}