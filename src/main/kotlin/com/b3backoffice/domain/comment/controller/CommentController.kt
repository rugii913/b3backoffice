package com.b3backoffice.domain.comment.controller

import com.b3backoffice.domain.comment.dto.CommentRequest
import com.b3backoffice.domain.comment.dto.CommentResponse
import com.b3backoffice.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/reviews/{reviewId}/comments")
@RestController
class CommentController (
    private val commentService: CommentService
){

    @GetMapping
    fun getCommentList(@PathVariable reviewId: Long) : ResponseEntity<List<CommentResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(reviewId))
    }

    @PostMapping
    fun addComment(@PathVariable reviewId:Long, @RequestBody request:CommentRequest) : ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.addComment(reviewId, request))
    }

    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable reviewId: Long, @PathVariable commentId:Long, @RequestBody request: CommentRequest) : ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(reviewId, commentId, request))
    }

    @DeleteMapping("/{commentId}")
    fun removeComment(@PathVariable reviewId: Long, @PathVariable commentId: Long) : ResponseEntity<Unit>{
        commentService.removeComment(reviewId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}