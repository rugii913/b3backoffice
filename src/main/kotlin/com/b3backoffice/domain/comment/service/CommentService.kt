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
import com.b3backoffice.domain.user.repositiry.UserRepository
import com.b3backoffice.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository
){

    fun getCommentList(reviewId: Long, pageNumber:Int): Page<CommentResponse> {
        val pageable: PageRequest = PageRequest.of(pageNumber, 10)

        return commentRepository.findAllByReviewIdAndDeletedAtIsNull(reviewId, pageable).map { it.toResponse() }
    }

    @Transactional
    fun addComment(userId:Long, reviewId: Long, request: CommentRequest): CommentResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)

        return commentRepository.save(
            Comment(
                user = user,
                review = review,
                content = request.content,
            )
        ).toResponse()
    }

    @Transactional
    fun updateComment(user:UserPrincipal, reviewId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        val userRole = user.authorities.map { it.authority }
        val comment = commentRepository.findByReviewIdAndId(reviewId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if("ROLE_COMMON" in userRole && comment.user.id != user.id) throw UnauthorizedException()
        if(comment.deletedAt != null) throw DeletedCommentException()

        comment.content = request.content

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    fun removeComment(user:UserPrincipal, reviewId: Long, commentId: Long) {
        val userRole = user.authorities.map { it.authority }
        val comment = commentRepository.findByReviewIdAndId(reviewId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if("ROLE_COMMON" in userRole && comment.user.id != user.id) throw UnauthorizedException()
        if(comment.deletedAt != null) throw DeletedCommentException()

        comment.deletedAt = LocalDateTime.now()
        commentRepository.save(comment).toResponse()
    }
}