package com.b3backoffice.domain.user.dto

import com.b3backoffice.domain.user.model.User

data class UserDto(
        val id: Long?,
        val nickname: String,
        val role: String
){
    companion object{
        fun to(user: User): UserDto{
            return UserDto(
                    id = user.id,
                    nickname = user.profile.nickname,
                    role = user.role.toString()
            )
        }
    }
}
