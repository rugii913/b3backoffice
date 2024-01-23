package com.b3backoffice.domain.user.service

import com.b3backoffice.domain.exception.InvalidCredentialException
import com.b3backoffice.domain.user.dto.SignupArgument
import com.b3backoffice.domain.user.dto.UserDto
import com.b3backoffice.domain.user.model.Profile
import com.b3backoffice.domain.user.model.User
import com.b3backoffice.domain.user.repositiry.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userRepository: UserRepository
): UserService {
    override fun signup(request: SignupArgument): UserDto {
        if(userRepository.existsByUsername(request.username)){
            throw InvalidCredentialException("Username is already in use")
        }
        val result = userRepository.save(
                User(
                        username = request.username,
                        password = request.password,
                        profile = Profile(
                                email = request.email,
                                realName = request.realName,
                                nickname = request.nickname,
                                introduction = request.introduction
                        )
                )
        )
        return UserDto.to(result)
    }
}