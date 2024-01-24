package com.b3backoffice.domain.comment.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CommentRequest(
    @NotBlank(message = "내용을 작성해주세요.")
    @Size(min = 1, max = 100, message = "글자수는 100자를 초과할 수 없습니다.")
    val content:String
)