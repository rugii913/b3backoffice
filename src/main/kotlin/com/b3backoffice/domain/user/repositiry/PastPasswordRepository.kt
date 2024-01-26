package com.b3backoffice.domain.user.repositiry

import com.b3backoffice.domain.user.model.PastPassword
import com.b3backoffice.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PastPasswordRepository: JpaRepository<PastPassword, User> {
    // @Query("select p.pastPasswordFirst, p.pastPasswordSecond, p.pastPasswordThird from PastPassword p where p.user = :user")
    // fun findPastPasswords(user: User): Array<String>

    fun findByUser(user: User): PastPassword
}
