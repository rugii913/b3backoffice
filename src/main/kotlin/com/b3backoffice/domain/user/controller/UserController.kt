package com.b3backoffice.domain.user.controller

import com.b3backoffice.domain.exception.UnauthorizedException
import com.b3backoffice.domain.user.dto.*
import com.b3backoffice.domain.user.service.UserService
import com.b3backoffice.infra.security.UserPrincipal
import com.b3backoffice.infra.security.jwt.service.TokenInvalidationService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val tokenInvalidationService: TokenInvalidationService,
) {
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid signupArgument: SignupArgument): ResponseEntity<UserDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signup(signupArgument))
    }
    @PostMapping("/login")
    fun login(@RequestBody loginArgument: LoginArgument): ResponseEntity<LoginDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginArgument))
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<Unit> {
        val jwt = request.getBearerToken() ?: throw IllegalStateException("비정상 로그아웃 요청") // TODO 예외처리 다시
        tokenInvalidationService.expireToken(jwt)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PutMapping("/users/{userId}")
    fun updateProfile(@AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable userId:Long, @RequestBody updateProfileArgument: UpdateProfileArgument): ResponseEntity<Unit>{
        if(userPrincipal.id != userId) throw UnauthorizedException()

        userService.updateProfile(userId, updateProfileArgument)
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

    private fun HttpServletRequest.getBearerToken(): String?{
        val bearerPattern = Regex("^Bearer (.+?)$") // TODO - JwtAuthenticationFilter와 중복을 깔끔하게 제거하려면?
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return bearerPattern.find(headerValue)?.groupValues?.get(1)
    }
}