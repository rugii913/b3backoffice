package com.b3backoffice.domain.review.controller

import com.b3backoffice.domain.review.dto.ReviewCreateRequest
import com.b3backoffice.domain.review.dto.ReviewResponse
import com.b3backoffice.domain.review.dto.ReviewUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/reviews")
@RestController
class ReviewController {

    @GetMapping("/{reviewId}")
    fun getReview(@PathVariable reviewId: Long): ResponseEntity<ReviewResponse> {
        // TODO
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping
    fun getReviewList(): ResponseEntity<List<ReviewResponse>> {
        // TODO
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping
    fun createReview(@RequestBody request: ReviewCreateRequest): ResponseEntity<ReviewResponse> {
        // TODO
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @RequestBody request: ReviewUpdateRequest,
    ): ResponseEntity<ReviewResponse> {
        // TODO
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(
        @PathVariable reviewId: Long,
    ): ResponseEntity<Unit> {
        // TODO
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
