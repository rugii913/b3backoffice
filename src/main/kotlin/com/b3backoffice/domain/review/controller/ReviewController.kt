package com.b3backoffice.domain.review.controller

import com.b3backoffice.domain.review.dto.ReviewCreateRequest
import com.b3backoffice.domain.review.dto.ReviewResponse
import com.b3backoffice.domain.review.dto.ReviewUpdateRequest
import com.b3backoffice.domain.review.service.ReviewService
import com.b3backoffice.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/reviews")
@RestController
class ReviewController(
    private val reviewService: ReviewService,
) {

    @GetMapping("/{reviewId}")
    fun getReview(@PathVariable reviewId: Long): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReview(reviewId))
    }

    @GetMapping
    fun getReviewList(): ResponseEntity<List<ReviewResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewList())
    }

    @PostMapping
    fun createReview(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: ReviewCreateRequest,
    ): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.createReview(userPrincipal.id, request))
    }

    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: ReviewUpdateRequest,
    ): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.updateReview(reviewId, userPrincipal.id, request))
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(
        @PathVariable reviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit> {
        reviewService.deleteReview(userPrincipal.id, reviewId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
