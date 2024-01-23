package com.b3backoffice.domain.comment.model

import com.b3backoffice.domain.review.model.Review
import com.b3backoffice.domain.shared.model.BaseEntity
import com.b3backoffice.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Comment(
    @ManyToOne @JoinColumn(name = "user_id") val user: User,
    @ManyToOne @JoinColumn(name = "review_id") val review: Review,
    var content: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var deletedAt: LocalDateTime? = null
}
