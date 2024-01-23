package com.b3backoffice.domain.review.model

import com.fasterxml.jackson.annotation.JsonCreator
import org.apache.commons.lang3.EnumUtils

enum class CuisineCategory(val categoryInKorean: String) {
    KOREAN("한식"),
    BUNSIK("분식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    WESTERN("양식"),
    ASIAN("아시아 음식"),
    PUB("술집"),
    CAFE_AND_DESSERT("카페, 디저트");

    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(name: String?): CuisineCategory =
            name?.let { EnumUtils.getEnumIgnoreCase(CuisineCategory::class.java, it.trim()) }
                ?: throw IllegalArgumentException("해당하는 음식 카테고리가 없습니다.") // TODO 예외 메시지
    }
}
