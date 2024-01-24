package com.b3backoffice.domain.comment.service

import com.b3backoffice.domain.comment.dto.CommentRequest
import com.b3backoffice.domain.comment.dto.CommentResponse
import com.b3backoffice.domain.comment.model.Comment
import com.b3backoffice.domain.comment.model.toResponse
import com.b3backoffice.domain.comment.repository.CommentRepository
import com.b3backoffice.domain.exception.DeletedCommentException
import com.b3backoffice.domain.exception.ModelNotFoundException
import com.b3backoffice.domain.exception.UnauthorizedException
import com.b3backoffice.domain.review.repository.ReviewRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val reviewRepository: ReviewRepository
){

    fun getCommentList(reviewId: Long):List<CommentResponse>{
        //TODO: 페이징
        return commentRepository.findAllByReviewIdAndDeletedAtIsNull(reviewId).map { it.toResponse() }
    }

    @Transactional
    fun addComment(reviewId: Long, request: CommentRequest): CommentResponse {

        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)

        return commentRepository.save(
            Comment(
                user = review.user,
                review = review,
                content = request.content,
            )
        ).toResponse()
    }

    @Transactional
    fun updateComment(userId:Long, reviewId: Long, commentId: Long, request: CommentRequest): CommentResponse {

        val comment = commentRepository.findByReviewIdAndId(reviewId, commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if(comment.user.id != userId) throw UnauthorizedException()
        if(comment.deletedAt == null) throw DeletedCommentException()

        comment.content = request.content

        return commentRepository.save(comment).toResponse()
    }

    fun removeComment(userId:Long, reviewId: Long, commentId: Long) {

        val comment = commentRepository.findByReviewIdAndId(reviewId, commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if(comment.user.id != userId) throw UnauthorizedException()
        if(comment.deletedAt == null) throw DeletedCommentException()

        comment.deletedAt = LocalDateTime.now()
    }
}