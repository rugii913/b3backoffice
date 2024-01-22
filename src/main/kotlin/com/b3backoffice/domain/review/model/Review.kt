package com.b3backoffice.domain.review.model

import com.b3backoffice.domain.shared.model.BaseEntity
import com.b3backoffice.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Review(
    @ManyToOne @JoinColumn(name = "user_id") val user: User,
    var storeName: String,
    var category: Category,
    var address: String,
    var visitedAt: LocalDateTime,
    var waitingTime: Int,
    var storeSize: Int,
    var cleanliness: Int,
    var content: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var deletedAt: LocalDateTime? = null
}
