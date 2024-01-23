package com.b3backoffice.domain.user.controller

import com.b3backoffice.domain.user.dto.SignupArgument
import com.b3backoffice.domain.user.dto.UserDto
import com.b3backoffice.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody signupArgument: SignupArgument): ResponseEntity<UserDto>{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signup(signupArgument))
    }
}