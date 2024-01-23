package com.b3backoffice.domain.comment.dto

import java.time.LocalDateTime

data class CommentResponse (
    //TODO: response 필드 재확인
    val id:Long,
    val userId:Long,
    val reviewId:Long,
    val content:String,
    val createdAt:LocalDateTime,
    val updatedAt:LocalDateTime,
    val deletedAt:LocalDateTime?
)