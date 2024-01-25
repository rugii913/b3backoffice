package com.b3backoffice.domain.notice.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateNoticeRequest(
    @field:NotBlank(message = "내용을 작성해주세요.")
    @field:Size(min = 1, max = 50, message = "글자수는 50자를 초과할 수 없습니다.")
    val title: String,

    @field:NotBlank(message = "내용을 작성해주세요.")
    @field:Size(min = 1, max = 1000, message = "글자수는 1000자를 초과할 수 없습니다.")
    val content: String,

)

