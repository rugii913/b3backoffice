package com.b3backoffice.domain.review.repository

import com.b3backoffice.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {

    fun findByIdAndDeletedAtIsNull(reviewId: Long): Review

    fun findAllByDeletedAtIsNull(): List<Review>
}
