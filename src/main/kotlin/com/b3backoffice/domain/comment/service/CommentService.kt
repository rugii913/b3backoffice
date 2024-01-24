package com.b3backoffice.domain.comment.service

import com.b3backoffice.domain.comment.dto.CommentRequest
import com.b3backoffice.domain.comment.dto.CommentResponse
import com.b3backoffice.domain.comment.model.toResponse
import com.b3backoffice.domain.comment.repository.CommentRepository
import com.b3backoffice.domain.exception.DeletedCommentException
import com.b3backoffice.domain.exception.ModelNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CommentService(
    private val commentRepository: CommentRepository
){

    fun getCommentList(reviewId: Long):List<CommentResponse>{
        //TODO: 삭제된 Comment는 가져오지 않기 (deleteAt)
        //TODO: 페이징
        return commentRepository.findAllByReviewId(reviewId).map { it.toResponse() }
    }

    @Transactional
    fun addComment(reviewId: Long, request: CommentRequest): CommentResponse {
        //TODO: 검증 - 1~100자 (0자인 경우, 100자 이상인 경우)
        //TODO: Review Repository 받아오면 review 있는지 검사하고 예외처리, Comment 생성자에 review, user 채우기

//        return commentRepository.save(
//            Comment(
//                content = request.content,
//            )
//        ).toResponse()

        TODO()
    }

    @Transactional
    fun updateComment(reviewId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        //TODO: 검증 - 1~100자 (0자인 경우, 100자 이상인 경우)
        //TODO: 인가

        val comment = commentRepository.findByReviewIdAndId(reviewId, commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if(comment.deletedAt == null) throw DeletedCommentException()

        comment.content = request.content

        return commentRepository.save(comment).toResponse()
    }

    fun removeComment(reviewId: Long, commentId: Long) {
        //TODO: 인가

        val comment = commentRepository.findByReviewIdAndId(reviewId, commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if(comment.deletedAt == null) throw DeletedCommentException()

        comment.deletedAt = LocalDateTime.now()
    }
}