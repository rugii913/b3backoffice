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
) : UserService {

    override fun signup(request: SignupArgument): UserDto {
        if (userRepository.existsByUsername(request.username)) {
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
        ).also {
            pastPasswordRepository.save(PastPassword(it))
        }
        return UserDto.to(result)
    }

    override fun login(request: LoginArgument): LoginDto {
        val foundUser = userRepository.findByUsername(request.username) ?: throw ModelNotFoundException("User", null)

        if (!passwordEncoder.matches(request.password, foundUser.password)) {
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

    override fun updatePassword(userId: Long, request: UpdatePasswordArgument): Unit {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        val pastPassword = pastPasswordRepository.findByUser(user)
        if (pastPassword.contains(request.password)) throw IllegalStateException("이미 사용한 비밀번호입니다.") // TODO 더 나은 예외 처리

        val requestedEncodedPassword: String = passwordEncoder.encode(request.password)
        user.updatePassword(requestedEncodedPassword).also { userRepository.save(it) }
        pastPassword.updatePastPassword(requestedEncodedPassword).also { pastPasswordRepository.save(it) }
    }

    private fun PastPassword.contains(requestedRawPassword: String): Boolean {
        return (passwordEncoder.matches(requestedRawPassword, pastPasswordFirst)
                || passwordEncoder.matches(requestedRawPassword, pastPasswordSecond)
                || passwordEncoder.matches(requestedRawPassword, pastPasswordThird))
    }
}