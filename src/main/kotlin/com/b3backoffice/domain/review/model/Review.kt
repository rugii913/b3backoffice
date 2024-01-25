package com.b3backoffice.domain.review.model

import com.b3backoffice.domain.review.dto.ReviewResponse
import com.b3backoffice.domain.review.dto.ReviewUpdateRequest
import com.b3backoffice.domain.shared.model.BaseEntity
import com.b3backoffice.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Review(
    @ManyToOne @JoinColumn(name = "user_id") val user: User,
    var storeName: String,
    @Enumerated(EnumType.STRING) var cuisineCategory: CuisineCategory,
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

    var countHeart: Int = 0
}

fun Review.toResponse(): ReviewResponse {
    return ReviewResponse(
        createdAt = this.createdAt,
        lastModifiedAt = this.lastModifiedAt,
        userNickname = this.user.profile.nickname,
        storeName = this.storeName,
        cuisineCategory = this.cuisineCategory,
        address = this.address,
        visitedAt = this.visitedAt,
        waitingTime = this.waitingTime,
        storeSize = this.storeSize,
        cleanliness = this.cleanliness,
        content = this.content,
        countHeart = countHeart
    )
}

fun Review.updateFrom(request: ReviewUpdateRequest): Review {
    this.storeName = request.storeName
    this.cuisineCategory = request.cuisineCategory
    this.address = request.address
    this.visitedAt = request.visitedAt
    this.waitingTime = request.waitingTime
    this.storeSize = request.storeSize
    this.cleanliness = request.cleanliness
    this.content = request.content

    return this
}
