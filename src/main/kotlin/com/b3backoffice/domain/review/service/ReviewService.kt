package com.b3backoffice.domain.review.service

import com.b3backoffice.domain.review.dto.ReviewCreateRequest
import com.b3backoffice.domain.review.dto.ReviewResponse
import com.b3backoffice.domain.review.dto.ReviewUpdateRequest
import com.b3backoffice.domain.review.model.Review
import com.b3backoffice.domain.review.model.toResponse
import com.b3backoffice.domain.review.model.updateFrom
import com.b3backoffice.domain.review.repository.ReviewRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
) {

    fun getReview(reviewId: Long): ReviewResponse {
        return reviewRepository.findByIdAndDeletedAtIsNull(reviewId).toResponse()
    }

    fun getReviewList(): List<ReviewResponse> {
        return reviewRepository.findAllByDeletedAtIsNull().map { it.toResponse() }
    }

    @Transactional
    fun createReview(request: ReviewCreateRequest): ReviewResponse {
        return Review(
            user = TODO(),
            storeName = request.storeName,
            cuisineCategory = request.cuisineCategory,
            address = request.address,
            visitedAt = request.visitedAt,
            waitingTime = request.waitingTime,
            storeSize = request.storeSize,
            cleanliness = request.cleanliness,
            content = request.content,
        ).let { reviewRepository.save(it) }
            .toResponse()
    }

    @Transactional
    fun updateReview(reviewId: Long, request: ReviewUpdateRequest): ReviewResponse {
        return reviewRepository.findByIdAndDeletedAtIsNull(reviewId).updateFrom(request) // TODO 예외 처리
            .let { reviewRepository.save(it) }
            .toResponse()
    }

    fun deleteReview(reviewId: Long): Unit {
        reviewRepository.findByIdOrNull(reviewId)
            ?.also { it.deletedAt = LocalDateTime.now() }
            .also { it ?: throw IllegalStateException("") } // TODO 예외 처리
    }
}
