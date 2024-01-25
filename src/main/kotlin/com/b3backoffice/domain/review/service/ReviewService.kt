package com.b3backoffice.domain.review.service

import com.b3backoffice.domain.exception.ModelNotFoundException
import com.b3backoffice.domain.exception.UnauthorizedException
import com.b3backoffice.domain.review.dto.ReviewCreateRequest
import com.b3backoffice.domain.review.dto.ReviewResponse
import com.b3backoffice.domain.review.dto.ReviewUpdateRequest
import com.b3backoffice.domain.review.model.Review
import com.b3backoffice.domain.review.model.toResponse
import com.b3backoffice.domain.review.model.updateFrom
import com.b3backoffice.domain.review.repository.ReviewRepository
import com.b3backoffice.domain.user.repositiry.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository,
) {

    fun getReview(reviewId: Long): ReviewResponse {
        return reviewRepository.findByIdAndDeletedAtIsNull(reviewId)?.toResponse()
            ?: throw ModelNotFoundException("Review", reviewId)
    }

    fun getReviewList(pageable: PageRequest): Page<ReviewResponse> {
        return reviewRepository.findAllByDeletedAtIsNull(pageable).map { it.toResponse() }
    }

    @Transactional
    fun createReview(userId: Long, request: ReviewCreateRequest): ReviewResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId) // TODO 적절한 예외 다시 한 번 고민

        return Review(
            user = user,
            storeName = request.storeName,
            cuisineCategory = request.cuisineCategory,
            address = request.address,
            visitedAt = request.visitedAt,
            waitingTime = request.waitingTime,
            storeSize = request.storeSize,
            cleanliness = request.cleanliness,
            content = request.content,
        )
            .let { reviewRepository.save(it) }
            .toResponse()
    }

    @Transactional
    fun updateReview(reviewId: Long, userId: Long, request: ReviewUpdateRequest): ReviewResponse {
        val review = reviewRepository.findByIdAndDeletedAtIsNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)

        return review.updateFrom(request)
            .also { if (it.user.id != userId) throw UnauthorizedException() }
            .let { reviewRepository.save(it) }
            .toResponse()
    }

    @Transactional
    fun deleteReview(reviewId: Long, userId: Long): Unit {
        val review = reviewRepository.findByIdAndDeletedAtIsNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)

        review.also { if (it.user.id != userId) throw UnauthorizedException() }
            .also { it.deletedAt = LocalDateTime.now() }
    }
}
