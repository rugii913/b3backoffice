package com.b3backoffice.domain.comment.repository

import com.b3backoffice.domain.comment.model.Comment
import com.b3backoffice.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findAllByReviewId(reviewId: Long) : List<Comment>
    fun findByReviewIdAndId(reviewId:Long, commentId:Long) : Comment?
}