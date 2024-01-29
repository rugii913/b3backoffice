package com.b3backoffice.infra.security.authFilter

import com.b3backoffice.domain.auth.exception.InvalidatedTokenException
import com.b3backoffice.domain.exception.dto.ErrorDto
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class InvalidatedTokenExceptionFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: InvalidatedTokenException) {
            // https://davidy87.tistory.com/19 참고 // 예외 잡은 후 처리 방식은 CustomAuthenticationEntryPoint을 그대로 사용함
            // TODO 더 나은 예외 처리 방법이 있는지 찾아볼 것
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"

            val objectMapper = ObjectMapper()
            val jsonString = objectMapper.writeValueAsString(ErrorDto("유효하지 않은 토큰입니다."))
            response.writer.write(jsonString)
        }
    }
}
