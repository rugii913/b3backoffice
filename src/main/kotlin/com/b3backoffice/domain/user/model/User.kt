package com.b3backoffice.domain.user.model

import com.b3backoffice.domain.shared.model.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class User(
    val username: String,
    var password: String,
    var email: String,
    val realName: String,
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
