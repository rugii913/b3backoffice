package com.b3backoffice.domain.notice.service

import com.b3backoffice.domain.exception.ModelNotFoundException
import com.b3backoffice.domain.notice.dto.CreateNoticeRequest
import com.b3backoffice.domain.notice.dto.NoticeResponse
import com.b3backoffice.domain.notice.dto.UpdateNoticeRequest
import com.b3backoffice.domain.notice.model.Notice
import com.b3backoffice.domain.notice.repository.NoticeRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class NoticeServiceImpl(
     private val noticeRepository: NoticeRepository
) : NoticeService_1
{
   override fun  getAllNoticeList(): List<NoticeResponse> {
       return noticeRepository.findAll().map { it.toResponse() }
   }

   override fun getNoticeById(noticeId: Long): NoticeResponse {
       val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw ModelNotFoundException("Notice", noticeId)
       return notice.toResponse()
   }
  @Transactional
   override fun createNotice(request: CreateNoticeRequest): NoticeResponse {
       return noticeRepository.save(
           Notice(
               title = request.title,
               content = request.content,
               user = request.user,
             )
       ) .toResponse()
   }
    @Transactional
    override fun updateNotice(noticeId: Long, request: UpdateNoticeRequest): NoticeResponse {
        val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw ModelNotFoundException("Notice", noticeId)
        val (title, context) = request

        notice.title = title
        notice.content =context

      return noticeRepository.save(notice).toResponse()
    }
    @Transactional
    override fun deleteNotice(noticeId: Long) {
        val notice = noticeRepository.findByIdOrNull(noticeId) ?:throw ModelNotFoundException("Notice", noticeId)
        noticeRepository.delete(notice)
    }

}