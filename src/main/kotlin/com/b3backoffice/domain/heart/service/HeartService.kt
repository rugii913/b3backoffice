package com.b3backoffice.domain.heart.service

import com.b3backoffice.domain.heart.dto.HeartResponse
import com.b3backoffice.domain.heart.model.Heart
import com.b3backoffice.domain.heart.repository.HeartRepository
import com.b3backoffice.domain.review.repository.ReviewRepository
import com.b3backoffice.domain.user.repositiry.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class HeartService(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository,
    private val heartRepository: HeartRepository
) {
    @Transactional
    fun reviewHeart(reviewId: Long, userId: Long): HeartResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("해당 사용자 없음")
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw IllegalArgumentException("해당 사용자 없음")

        val existingHeart = heartRepository.findByUserAndReview(user, review)

        return if (existingHeart != null) {
            heartRepository.deleteByUserAndReview(user, review)
            review.countHeart -= 1
            reviewRepository.save(review)
            HeartResponse(message = "좋아요 취소")
        } else {
            heartRepository.save(Heart(user=user, review = review))
            review.countHeart += 1
            reviewRepository.save(review)
            HeartResponse(message = "좋아요 성공")
        }
    }
}