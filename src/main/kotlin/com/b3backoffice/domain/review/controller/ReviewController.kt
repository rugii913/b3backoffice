package com.b3backoffice.domain.review.controller

import com.b3backoffice.domain.review.dto.ReviewCreateRequest
import com.b3backoffice.domain.review.dto.ReviewResponse
import com.b3backoffice.domain.review.dto.ReviewUpdateRequest
import com.b3backoffice.domain.review.service.ReviewService
import com.b3backoffice.infra.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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
    fun getReviewList(
        @RequestParam page: Int = 0,
    ): ResponseEntity<Page<ReviewResponse>> {
        val pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.Direction.DESC, "id")

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewList(pageable))
    }

    @PreAuthorize("hasRole('COMMON')")
    @PostMapping
    fun createReview(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @Valid @RequestBody request: ReviewCreateRequest,
    ): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.createReview(userPrincipal.id, request))
    }

    @PreAuthorize("hasRole('COMMON')")
    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @Valid @RequestBody request: ReviewUpdateRequest,
    ): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.updateReview(reviewId, userPrincipal.id, request))
    }

    @PreAuthorize("hasRole('COMMON')")
    @DeleteMapping("/{reviewId}")
    fun deleteReview(
        @PathVariable reviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit> {
        reviewService.deleteReview(userPrincipal.id, reviewId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }
}
