package com.b3backoffice.domain.notice.repository

import com.b3backoffice.domain.notice.model.Notice
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository: JpaRepository<Notice,Long > {


}