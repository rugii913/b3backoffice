package com.b3backoffice.domain.heart.controller

import com.b3backoffice.domain.heart.dto.HeartResponse
import com.b3backoffice.domain.heart.service.HeartService
import com.b3backoffice.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reviews/{reviewId}/hearts")
class HeartController(
    private val heartService: HeartService
) {

    @PostMapping
    fun reviewHeart(
        @PathVariable reviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<HeartResponse>{
        val heart = heartService.reviewHeart(reviewId, userPrincipal.id)
        return ResponseEntity.status(HttpStatus.OK).body(heart)
    }
}