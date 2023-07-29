plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")

    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

group = "com.jaenyeong"
version = "1.0.0"

dependencies {
    val kotestVersion: String by project

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // 등록된 모든 빈을 조회하기 위해 의존성 추가 및 `application.yml`에 bean 설정 추가
    // (`/actuator/beans` 경로를 통해 조회 가능)
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // `ProviderCountClient` 클래스의 `Provider` 인터페이스 사용을 위해 의존성 추가
    implementation("jakarta.inject:jakarta.inject-api:2.0.1")

    runtimeOnly("com.h2database:h2")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}
