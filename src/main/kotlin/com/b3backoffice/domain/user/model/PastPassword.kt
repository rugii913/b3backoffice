package com.b3backoffice.domain.user.model

import jakarta.persistence.*

@Entity
class PastPassword(
    _user: User,
    // @OneToOne을 구글링해보면 아마도 같은 책을 베낀 듯한 여러 자료들이 나옴
    // 객체지향을 살리려면 User가 PastPassword를 알고 있는 것이 좋겠지만, 추후 PastPassword가 더 변경 가능성이 크다고 판단하여 PastPassword 쪽에서 user_id를 갖고 있게 하였음
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
    @OneToOne @JoinColumn(name = "user_id") val user: User = _user
    var pastPasswordFirst: String = _user.password
    var pastPasswordSecond: String = ""
    var pastPasswordThird: String = ""

    fun updatePastPassword(requestedPassword: String): PastPassword {
        pastPasswordThird = pastPasswordSecond
        pastPasswordSecond = pastPasswordFirst
        pastPasswordFirst = requestedPassword
        return this
    }
}
