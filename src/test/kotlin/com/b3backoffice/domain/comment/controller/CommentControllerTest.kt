package com.b3backoffice.domain.comment.controller

import com.b3backoffice.domain.comment.dto.CommentRequest
import com.b3backoffice.domain.comment.dto.CommentResponse
import com.b3backoffice.domain.comment.service.CommentService
import com.b3backoffice.infra.security.UserPrincipal
import com.b3backoffice.infra.security.jwt.JwtPlugin
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.MockKException
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.bind.annotation.RequestBody
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class CommentControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin
) : DescribeSpec({
    extension(SpringExtension)

    // describe 끝날 때마다 설정했던 mocking 비워줌
    afterContainer {
        clearAllMocks()
    }

    // CommentService mocking
    val commentService = mockk<CommentService>()

    // COMMON - addComment
    describe("POST /reviews/{reviewId}/comments") {
        context("댓글 생성을 요청할 때") {
            it("201 status code를 응답한다.") {

                val reviewId = 1L

                // Mock 동작 정의
                every { commentService.addComment(any(), reviewId, CommentRequest("test comment")) } returns CommentResponse(
                    id = 1L,
                    userId = 1L,
                    reviewId = reviewId,
                    content = "test comment",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                    deletedAt = null
                )

                // access token 생성
                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    username = "tester@gmail.com",
                    role = "COMMON"
                )

                // 요청 수행
                val result = mockMvc.perform(
                    post("/reviews/$reviewId/comments")
                        .header("Authorization", "Bearer $jwtToken")
                        .content("{\"content\": \"test comment\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()

                // status code 확인
                result.response.status shouldBe 201
            }
        }
    }
})