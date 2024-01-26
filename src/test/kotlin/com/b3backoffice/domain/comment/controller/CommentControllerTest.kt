package com.b3backoffice.domain.comment.controller

import com.b3backoffice.domain.comment.dto.CommentResponse
import com.b3backoffice.domain.comment.service.CommentService
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
import org.springframework.data.domain.Page
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class CommentControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin
) : DescribeSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val commentService = mockk<CommentService>()

    describe("POST /reviews/{reviewId}/comments") {
        context("존재하는 review에 존재하는 user가 댓글 생성을 요청할 때") {
            it("201 status code를 응답한다.") {
                val userId = 1L
                val reviewId = 1L

                every { commentService.addComment(any(), any(), any()) } returns CommentResponse(
                    id = 1,
                    userId = userId,
                    reviewId = reviewId,
                    content = "test",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                    deletedAt = null
                )

                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    username = "tester",
                    role = "COMMON"
                )

                val result = mockMvc.perform(
                    post("/reviews/$reviewId/comments")
                        .header("Authorization", "Bearer $jwtToken")
                ).andReturn()

                result.response.status shouldBe 201

                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    CommentResponse::class.java
                )

                responseDto.reviewId shouldBe reviewId
            }
        }
    }
})