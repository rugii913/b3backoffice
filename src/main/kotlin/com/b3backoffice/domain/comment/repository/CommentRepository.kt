package com.b3backoffice.domain.comment.repository

import com.b3backoffice.domain.comment.model.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findAllByReviewIdAndDeletedAtIsNull(reviewId: Long, pagable:Pageable) : Page<Comment>

    fun findByReviewIdAndId(reviewId:Long, commentId:Long) : Comment?
}