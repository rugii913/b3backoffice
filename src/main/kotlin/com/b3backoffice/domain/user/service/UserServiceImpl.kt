package com.b3backoffice.domain.user.service

import com.b3backoffice.domain.exception.InvalidCredentialException
import com.b3backoffice.domain.exception.ModelNotFoundException
import com.b3backoffice.domain.user.dto.*
import com.b3backoffice.domain.user.model.PastPassword
import com.b3backoffice.domain.user.model.Profile
import com.b3backoffice.domain.user.model.User
import com.b3backoffice.domain.user.repositiry.PastPasswordRepository
import com.b3backoffice.domain.user.repositiry.UserRepository
import com.b3backoffice.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val pastPasswordRepository: PastPasswordRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
): UserService {
    override fun signup(request: SignupArgument): UserDto {
        if(userRepository.existsByUsername(request.username)){
            throw InvalidCredentialException("Username is already in use")
        }
        val result = userRepository.save(
                User(
                        username = request.username,
                        password = passwordEncoder.encode(request.password),
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

    override fun login(request: LoginArgument): LoginDto {
        val foundUser = userRepository.findByUsername(request.username) ?: throw ModelNotFoundException("User", null)

        if(!passwordEncoder.matches(request.password, foundUser.password)){
            throw IllegalStateException()
        }

        val loginDto = LoginDto(
            accessToken = jwtPlugin.generateAccessToken(
                subject = foundUser.id.toString(),
                username = foundUser.username,
                role = foundUser.role.toString()
            )
        )

        return loginDto
    }

    override fun updateProfile(userId: Long, request: UpdateProfileArgument) {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)

        user.profile.email = request.email
        user.profile.nickname = request.nickname
        user.profile.introduction = request.introduction

        userRepository.save(user)
    }

    override fun updatePassword(userId: Long, request: UpdatePasswordArgument) {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        val pastPassword = pastPasswordRepository.findByUser(user)

        if (isPastPassword(pastPassword, request.password)) throw IllegalStateException("해당 비밀번호 변경 불가") // TODO 예외 처리 다시

        updatePastPassword(pastPassword, request.password)
        updateUserPassword(user, request.password)
    }

    private fun isPastPassword(pastPassword: PastPassword, requestedPassword: String): Boolean {
        var result = false
        if (
            pastPassword.pastPasswordFirst == requestedPassword
            || pastPassword.pastPasswordSecond == requestedPassword
            || pastPassword.pastPasswordThird == requestedPassword
        ) result = true
        return result
    }

    private fun updatePastPassword(pastPassword: PastPassword, requestedPassword: String) {
        pastPassword.pastPasswordThird = pastPassword.pastPasswordSecond
        pastPassword.pastPasswordSecond = pastPassword.pastPasswordFirst
        pastPassword.pastPasswordFirst = requestedPassword

        pastPasswordRepository.save(pastPassword)
    }

    private fun updateUserPassword(user: User, requestedPassword: String) {
        user.password = requestedPassword
        userRepository.save(user)
    }
}