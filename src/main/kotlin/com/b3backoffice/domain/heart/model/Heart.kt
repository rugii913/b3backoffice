package com.b3backoffice.domain.heart.model

import com.b3backoffice.domain.review.model.Review
import com.b3backoffice.domain.user.model.User
import jakarta.persistence.*

@Entity
data class Heart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne @JoinColumn(name = "user_id") val user: User,
    @ManyToOne @JoinColumn(name = "review_id") val review: Review
)
