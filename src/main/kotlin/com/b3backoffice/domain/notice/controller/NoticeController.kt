package com.b3backoffice.domain.notice.controller

import com.b3backoffice.domain.notice.dto.CreateNoticeRequest
import com.b3backoffice.domain.notice.dto.NoticeResponse
import com.b3backoffice.domain.notice.dto.UpdateNoticeRequest
import com.b3backoffice.domain.notice.service.NoticeService
import com.b3backoffice.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RequestMapping("/notices")
@RestController
class NoticeController(
    private val noticeService: NoticeService
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
                .body(noticeService.getNoticeById(noticeId))
    }

     @PostMapping
     fun createNotice(
         @AuthenticationPrincipal userPrincipal: UserPrincipal,
         @RequestBody createNoticeRequest: CreateNoticeRequest,
     ) : ResponseEntity<NoticeResponse> {

         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(noticeService.createNotice(userPrincipal.id, createNoticeRequest))
     }

     @PutMapping("/notice/{noticeId}")
     fun updateNotice(
         @PathVariable noticeId: Long,
         @RequestBody updateNoticeRequest: UpdateNoticeRequest,
     ) :ResponseEntity<NoticeResponse>{
         return ResponseEntity
               .status(HttpStatus.OK)
               .body(noticeService.updateNotice(noticeId, updateNoticeRequest))
     }

    @DeleteMapping("/notices/{noticeId}")
    fun deleteNotice(@PathVariable noticeId: Long): ResponseEntity<Unit> {
        noticeService.deleteNotice(noticeId)
        return ResponseEntity
               .status(HttpStatus.NO_CONTENT)
               .build()

    }
}