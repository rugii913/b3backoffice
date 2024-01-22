package com.b3backoffice.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Profile(
    @Column(name = "email") var email: String,
    @Column(name = "real_name") val realName: String,
    @Column(name = "nickname") var nickname: String,
    @Column(name = "introduction") var introduction: String,
)
