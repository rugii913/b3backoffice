package com.b3backoffice.domain.user.model

import jakarta.persistence.*

@Entity
class PastPassword(
    @OneToOne @JoinColumn(name = "user_id") val user: User,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
    var pastPasswordFirst: String = ""
    var pastPasswordSecond: String = ""
    var pastPasswordThird: String = ""

    fun updatePastPassword(requestedPassword: String): PastPassword {
        pastPasswordThird = pastPasswordSecond
        pastPasswordSecond = pastPasswordFirst
        pastPasswordFirst = requestedPassword
        return this
    }
}
