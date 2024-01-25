package com.b3backoffice.domain.notice.dto

import java.time.LocalDateTime


data class NoticeResponse(
    val id: Long,
    val userid: Long,
    val title: String,
    val content: String,
    var createdAt : LocalDateTime,
    var updatedAt : LocalDateTime,
)

