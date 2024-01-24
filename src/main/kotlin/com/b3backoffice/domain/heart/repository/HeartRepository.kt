package com.b3backoffice.domain.heart.repository

import com.b3backoffice.domain.heart.model.Heart
import com.b3backoffice.domain.review.model.Review
import com.b3backoffice.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface HeartRepository: JpaRepository<Heart, Long> {
    fun deleteByUserAndReview(user: User, review: Review)
    fun findByUserAndReview(user: User, review: Review): Heart?
}