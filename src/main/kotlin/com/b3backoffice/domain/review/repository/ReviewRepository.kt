package com.b3backoffice.domain.review.repository

import com.b3backoffice.domain.review.model.Review
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {

    fun findByIdAndDeletedAtIsNull(reviewId: Long): Review?

    fun findAllByDeletedAtIsNull(pageable: PageRequest): Page<Review>
}
