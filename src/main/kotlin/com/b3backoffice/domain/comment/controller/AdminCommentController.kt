package com.b3backoffice.domain.comment.controller

import com.b3backoffice.domain.comment.dto.CommentRequest
import com.b3backoffice.domain.comment.dto.CommentResponse
import com.b3backoffice.domain.comment.service.CommentService
import com.b3backoffice.infra.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/admin/reviews/{reviewId}/comments/{commentId}")
@RestController
class AdminCommentController(
    private val commentService: CommentService
) {

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    fun updateComment( @AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable reviewId: Long, @PathVariable commentId:Long, @RequestBody @Valid request: CommentRequest) : ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(userPrincipal, reviewId, commentId, request))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    fun removeComment(@AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable reviewId: Long, @PathVariable commentId: Long) : ResponseEntity<Unit> {
        commentService.removeComment(userPrincipal, reviewId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}