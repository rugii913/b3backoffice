package com.b3backoffice.domain.notice.service

import com.b3backoffice.domain.exception.ModelNotFoundException
import com.b3backoffice.domain.notice.dto.CreateNoticeRequest
import com.b3backoffice.domain.notice.dto.NoticeResponse
import com.b3backoffice.domain.notice.dto.UpdateNoticeRequest
import com.b3backoffice.domain.notice.model.Notice
import com.b3backoffice.domain.notice.repository.NoticeRepository
import com.b3backoffice.domain.user.repositiry.UserRepository
import jakarta.transaction.Transactional

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class NoticeService(
     private val noticeRepository: NoticeRepository,
    private val userRepository: UserRepository,
)
{
    fun getAllNoticeList(): List<NoticeResponse> {
       return noticeRepository.findAll().map { it.toResponse() }
   }

    fun getNoticeById(noticeId: Long): NoticeResponse {
       val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw ModelNotFoundException("Notice", noticeId)
       return notice.toResponse()
   }
  @Transactional
    fun createNotice(userId: Long, request: CreateNoticeRequest): NoticeResponse {

        val foundUser = userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("적절한 예외처리 필요함") // TODO 적절한 예외처리 필요함

       return noticeRepository.save(
           Notice(
               title = request.title,
               content = request.content,
               user = foundUser
             )
       ).toResponse()
   }
    @Transactional
     fun updateNotice(noticeId: Long, userId: Long, request: UpdateNoticeRequest): NoticeResponse {
        val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw ModelNotFoundException("Notice", noticeId)
        if (notice.user.id != userId) throw IllegalArgumentException("요청한 사용자와 Notice 작성한 사용자가 다릅니다.") // TODO 더 적절한 예외 처리 필요

        val (title, context) = request

        notice.title = title
        notice.content =context

      return noticeRepository.save(notice).toResponse()
    }
    @Transactional
     fun deleteNotice(noticeId: Long, userId: Long) {
        val notice = noticeRepository.findByIdOrNull(noticeId) ?:throw ModelNotFoundException("Notice", noticeId)
        if (notice.user.id != userId) throw IllegalArgumentException("요청한 사용자와 Notice 작성한 사용자가 다릅니다.") // TODO 더 적절한 예외 처리 필요
        noticeRepository.delete(notice)
    }

}