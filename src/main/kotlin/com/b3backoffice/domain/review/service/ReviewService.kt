package com.b3backoffice.domain.review.service

import com.b3backoffice.domain.review.dto.ReviewCreateRequest
import com.b3backoffice.domain.review.dto.ReviewResponse
import com.b3backoffice.domain.review.dto.ReviewUpdateRequest
import com.b3backoffice.domain.review.model.Review
import com.b3backoffice.domain.review.model.toResponse
import com.b3backoffice.domain.review.model.updateFrom
import com.b3backoffice.domain.review.repository.ReviewRepository
import com.b3backoffice.domain.user.repositiry.UserRepository
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
        return reviewRepository.findByIdAndDeletedAtIsNull(reviewId)
            ?.toResponse()
            ?: throw IllegalArgumentException("해당 review 없음") // TODO 예외 처리 다시
    }

    fun getReviewList(): List<ReviewResponse> {
        return reviewRepository.findAllByDeletedAtIsNull().map { it.toResponse() }
    }

    @Transactional
    fun createReview(userId: Long, request: ReviewCreateRequest): ReviewResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("해당 사용자 없음") // TODO 예외 처리 다시

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
        val review = reviewRepository.findByIdAndDeletedAtIsNull(reviewId)
            ?: throw IllegalArgumentException("해당 사용자 없음") // TODO 예외 처리 다시 - 데이터 없거나 삭제된 경우 예외 처리

        return review.updateFrom(request)
            .also { if (it.user.id != userId) throw IllegalStateException("권한 없는 사용자") } // TODO 예외 처리 다시
            .let { reviewRepository.save(it) }
            .toResponse()
    }

    @Transactional
    fun deleteReview(reviewId: Long, userId: Long): Unit {
        val review = reviewRepository.findByIdAndDeletedAtIsNull(reviewId)
            ?: throw IllegalArgumentException("해당 사용자 없음") // TODO 예외 처리 다시 - 데이터 없거나 삭제된 경우 예외 처리

        review.also { if (it.user.id != userId) throw IllegalStateException("권한 없는 사용자") } // TODO 예외 처리 다시
            .also { it.deletedAt = LocalDateTime.now() }
    }
}
