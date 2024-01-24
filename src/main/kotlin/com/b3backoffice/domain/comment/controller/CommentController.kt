package com.b3backoffice.domain.comment.controller

import com.b3backoffice.domain.comment.dto.CommentRequest
import com.b3backoffice.domain.comment.dto.CommentResponse
import com.b3backoffice.domain.comment.service.CommentService
import com.b3backoffice.infra.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/reviews/{reviewId}/comments")
@RestController
class CommentController (
    private val commentService: CommentService
){

    @GetMapping
    fun getCommentList(@PathVariable reviewId: Long, @RequestParam(value = "pageNum", defaultValue = "0") pageNum: Int) : ResponseEntity<Page<CommentResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(reviewId, pageNum))
    }

    @PostMapping
    fun addComment(@AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable reviewId:Long, @RequestBody @Valid request:CommentRequest) : ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.addComment(userPrincipal.id, reviewId, request))
    }

    @PutMapping("/{commentId}")
    fun updateComment(@AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable reviewId: Long, @PathVariable commentId:Long, @RequestBody @Valid request: CommentRequest) : ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(userPrincipal.id, reviewId, commentId, request))
    }

    @DeleteMapping("/{commentId}")
    fun removeComment(@AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable reviewId: Long, @PathVariable commentId: Long) : ResponseEntity<Unit>{
        commentService.removeComment(userPrincipal.id, reviewId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}