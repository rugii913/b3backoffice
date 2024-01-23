package com.b3backoffice.domain.review.dto

import com.b3backoffice.domain.review.model.CuisineCategory
import java.time.LocalDateTime

data class ReviewResponse(
    val createdAt: LocalDateTime,
    val lastModifiedAt: LocalDateTime,
    val userNickname: String,
    val storeName: String,
    val cuisineCategory: CuisineCategory,
    val address: String,
    val visitedAt: LocalDateTime,
    val waitingTime: Int,
    val storeSize: Int,
    val cleanliness: Int,
    val content: String,
)
