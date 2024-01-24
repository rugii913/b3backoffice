package com.b3backoffice.domain.notice.model

import com.b3backoffice.domain.notice.dto.NoticeResponse
import com.b3backoffice.domain.shared.model.BaseEntity
import com.b3backoffice.domain.user.model.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime


@Entity
class Notice(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    var title: String,
    var content: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var deletedAt: LocalDateTime? = null

    fun toResponse(): NoticeResponse {
        return NoticeResponse(
            id = id!!,
            userid = this.user.id!!,
            title = title,
            content = content,
            createdAt = createdAt,
            updatedAt = lastModifiedAt,
        )
    }

}


