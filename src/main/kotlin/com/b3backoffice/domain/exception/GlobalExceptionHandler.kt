package com.b3backoffice.domain.exception

import com.b3backoffice.domain.exception.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorDto> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorDto(message = e.message))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorDto> {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorDto(message = e.message))
    }

    @ExceptionHandler(InvalidCredentialException::class)
    fun handleInvalidCredentialException(e: InvalidCredentialException): ResponseEntity<ErrorDto> {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorDto(e.message))
    }

    @ExceptionHandler(DeletedCommentException::class)
    fun handleDeletedCommentException(e: DeletedCommentException): ResponseEntity<ErrorDto> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorDto(e.message))
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(e: UnauthorizedException): ResponseEntity<ErrorDto> {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ErrorDto(e.message))
    }
}