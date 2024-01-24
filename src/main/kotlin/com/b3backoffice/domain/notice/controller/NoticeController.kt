package com.b3backoffice.domain.notice.controller

import com.b3backoffice.domain.notice.dto.CreateNoticeRequest
import com.b3backoffice.domain.notice.dto.NoticeResponse
import com.b3backoffice.domain.notice.dto.UpdateNoticeRequest
import com.b3backoffice.domain.notice.service.NoticeService
import com.b3backoffice.domain.notice.service.NoticeServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/notices")
@RestController
class NoticeController(
    private val noticeService: NoticeServiceImpl
) {

    @GetMapping
    fun getNoticeList(): ResponseEntity<List<NoticeResponse>> {
        return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(noticeService.getAllNoticeList())
    }

    @GetMapping("/{noticeId}")
    fun getNotice(@PathVariable noticeId: Long) : ResponseEntity<NoticeResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(NoticeService.getNoticeById(userId))
    }

     @PostMapping
     fun createNotice(@RequestParam createNoticeRequest: CreateNoticeRequest) : ResponseEntity<NoticeResponse> {

         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(noticeService.createNotice(createNoticeRequest))
     }

     @PutMapping("/notice/{noticeId}")
     fun updateNotice(@PathVariable noticeId: Long) :ResponseEntity<NoticeResponse>{
         return ResponseEntity
               .status(HttpStatus.OK)
               .body(noticeService.updateNotice(noticeId,UpdateNoticeRequest))
     }

    @DeleteMapping("/notices/{noticeId}")
    fun deleteNotice(@PathVariable noticeId: Long): ResponseEntity<Unit> {
        noticeService.deleteNotice(noticeId)
        return ResponseEntity
               .status(HttpStatus.NO_CONTENT)
               .build()

    }
}