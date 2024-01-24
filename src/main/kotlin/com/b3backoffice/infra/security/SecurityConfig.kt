package com.b3backoffice.infra.security

import com.b3backoffice.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity, introspector: HandlerMappingIntrospector): SecurityFilterChain {
        return httpSecurity
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .headers { it.frameOptions { frameOptionsConfig -> frameOptionsConfig.sameOrigin() } } // 콘솔 H2 사용 위해서 필요
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/**",// 개발 초기 단계 - 우선 모든 요청에 대해 인증 없이 열어놓음
                ).permitAll()
                    .requestMatchers( // https://colabear754.tistory.com/170 - Spring Security 사용하면서 H2 콘솔 사용
                        MvcRequestMatcher(introspector, "/**").apply { setServletPath("/h2-console") }
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}
