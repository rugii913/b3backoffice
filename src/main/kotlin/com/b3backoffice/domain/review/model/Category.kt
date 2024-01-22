package com.b3backoffice.domain.review.model

enum class Category(val categoryInKorean: String) {
    KOREAN("한식"),
    BUNSIK("분식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    WESTERN("양식"),
    ASIAN("아시아 음식"),
    PUB("술집"),
    CAFE_AND_DESSERT("카페, 디저트");
}
