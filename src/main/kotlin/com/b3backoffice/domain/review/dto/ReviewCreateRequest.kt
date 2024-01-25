package com.b3backoffice.domain.review.dto

import com.b3backoffice.domain.review.model.CuisineCategory
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Range
import java.time.LocalDateTime

data class ReviewCreateRequest(
    @field:NotBlank @field:Size(min = 1, max = 20) val storeName: String,
    val cuisineCategory: CuisineCategory,
    @field:NotBlank @field:Size(min = 1, max = 100) val address: String,
    val visitedAt: LocalDateTime,
    @field:Range(min = 0, max = 720) val waitingTime: Int,
    @field:Range(min = 1, max = 3) val storeSize: Int,
    @field:Range(min = 1, max = 3) val cleanliness: Int,
    @field:NotBlank val content: String,
)
