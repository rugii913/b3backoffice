package com.b3backoffice.domain.comment.service

import com.b3backoffice.domain.comment.repository.CommentRepository
import com.b3backoffice.domain.exception.ModelNotFoundException
import com.b3backoffice.domain.review.repository.ReviewRepository
import com.b3backoffice.domain.user.repositiry.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
@ExtendWith(MockKExtension::class)
class CommentServiceTest : BehaviorSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val commentRepository = mockk<CommentRepository>()
    val reviewRepository = mockk<ReviewRepository>()
    val userRepository = mockk<UserRepository>()

    Given("reviewId에 해당하는 Review가 존재하지 않을 때") {
        When("해당 Review의 Comment 생성을 요청하면") {
            Then("Review에 대한 ModelNotFoundException이 발생해야 한다.") {

                val reviewId = 1L
                every { reviewRepository.findByIdOrNull(reviewId) } returns null

                shouldThrow<ModelNotFoundException> {
                    reviewRepository.findByIdOrNull(reviewId)
                }
            }
        }
    }
})