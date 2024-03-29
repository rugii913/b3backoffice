package com.b3backoffice.domain.auth.controller

import com.b3backoffice.domain.auth.dto.ReissueAccessTokenRequest
import com.b3backoffice.domain.auth.dto.ReissueAccessTokenResponse
import com.b3backoffice.domain.auth.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/my-reissued-token")
    fun reissueTokens(@RequestBody request: ReissueAccessTokenRequest): ResponseEntity<ReissueAccessTokenResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(authService.reissueTokens(request.refreshToken))
    }
}
