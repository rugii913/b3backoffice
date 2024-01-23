package com.b3backoffice.domain.review.service

import com.b3backoffice.domain.review.dto.ReviewCreateRequest
import com.b3backoffice.domain.review.dto.ReviewResponse
import com.b3backoffice.domain.review.dto.ReviewUpdateRequest
import org.springframework.stereotype.Service

@Service
class ReviewService {

    fun getReview(reviewId: Long): ReviewResponse {
        TODO("Not yet implemented")
    }

    fun getReviewList(): List<ReviewResponse> {
        TODO("Not yet implemented")
    }

    fun createReview(request: ReviewCreateRequest): ReviewResponse {
        TODO("Not yet implemented")
    }

    fun updateReview(reviewId: Long, request: ReviewUpdateRequest): ReviewResponse {
        TODO("Not yet implemented")
    }

    fun deleteReview(reviewId: Long): Unit {
        TODO("Not yet implemented")
    }
}
