package com.b3backoffice.domain.user.model

import com.b3backoffice.domain.shared.model.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class User(
    var email: String,
    var password: String,
    val name: String,
    var nickname: String,
    var introduction: String,
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var deletedAt: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole = UserRole.COMMON
}
