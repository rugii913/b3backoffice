package com.b3backoffice.domain.comment.repository

import com.b3backoffice.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CommentRepository : JpaRepository<Comment, Long> {

    //fun findAllByReviewId(reviewId: Long) : List<Comment>

    @Query("select c from Comment c where c.review.id = :reviewId and c.deletedAt is null")
    fun findAllByReviewIdAndDeletedAt(reviewId: Long) : List<Comment>

    fun findByReviewIdAndId(reviewId:Long, commentId:Long) : Comment?
}