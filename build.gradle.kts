import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    kotlin("kapt") version "1.8.22" //test
    // allOpen 플러그인은 plugin.spring에, noArg 플러그인은 plugin.jpa에 포함됨
}

group = "com"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

// Kotlin에서 JPA를 사용하기 위해 noArg, allOpen 플러그인 적용 대상 지정
noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    // kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // spring web(spring boot starter)
    implementation("org.springframework.boot:spring-boot-starter-web")

    // spring data jpa(spring boot starter)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // spring security(spring boot starter)
    implementation("org.springframework.boot:spring-boot-starter-security")

    // spring bean validation(spring boot starter)
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Jwt
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

    // DB driver - H2
    runtimeOnly("com.h2database:h2")
    // DB driver - Postgresql
    runtimeOnly("org.postgresql:postgresql")

    // configuration processor(spring boot)
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // openapi - swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // test - spring boot starter
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // test - spring security test
    testImplementation("org.springframework.security:spring-security-test")
    // test - kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.5.5") //5.7.2 -> 5.5.5
    testImplementation("io.kotest:kotest-assertions-core:5.5.5") //5.7.2 -> 5.5.5
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.postgresql:postgresql")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test>().configureEach() {
    useJUnitPlatform()
}
