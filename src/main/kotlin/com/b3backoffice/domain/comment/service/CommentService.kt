package com.b3backoffice.domain.comment.service

import com.b3backoffice.domain.comment.dto.CommentRequest
import com.b3backoffice.domain.comment.dto.CommentResponse
import com.b3backoffice.domain.comment.model.Comment
import com.b3backoffice.domain.comment.model.toResponse
import com.b3backoffice.domain.comment.repository.CommentRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentService(
    private val commentRepository: CommentRepository
){

    fun getCommentList(reviewId: Long):List<CommentResponse>{
        return commentRepository.findAllByReviewId(reviewId).map { it.toResponse() }
    }

    fun addComment(reviewId: Long, request: CommentRequest): CommentResponse {
        //TODO: 검증 - 1~100자
        //TODO: Review Repository 받아오면 review 있는지 검사하고 예외처리, Comment 생성자에 review, user 채우기

//        return commentRepository.save(
//            Comment(
//                content = request.content,
//            )
//        ).toResponse()

        TODO()
    }

    fun updateComment(reviewId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        //TODO: 검증 - 1~100자
        //TODO: 인가
        //TODO: Custom 예외로 변경

        val comment = commentRepository.findByReviewIdAndId(reviewId, commentId) ?: throw EntityNotFoundException()
        comment.content = request.content

        return commentRepository.save(comment).toResponse()
    }

    fun removeComment(reviewId: Long, commentId: Long) {
        //TODO: 인가
        //TODO: soft delete

        val comment = commentRepository.findByReviewIdAndId(reviewId, commentId) ?: throw EntityNotFoundException()
        comment.deletedAt = LocalDateTime.now()

        TODO()
    }
}