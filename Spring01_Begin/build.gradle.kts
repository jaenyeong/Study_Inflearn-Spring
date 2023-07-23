plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")

    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

// 순수 코틀린 프로젝트에서는 무관한 설정이나
// Java 코드가 섞여 있거나 사용하는 Java 라이브러리가 특정 JVM 버전을 요구할 수 있기 때문에 설정
java.sourceCompatibility = JavaVersion.VERSION_17
group = "com.jaenyeong"
version = "1.0.0"

dependencies {
    val kotestVersion: String by project

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    runtimeOnly("com.h2database:h2")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // Kotest가 JUnit 플랫폼 위에서 실행될 수 있도록 해주는 Runner
    // assertion core 의존성을 포함하고 있어 따로 assertion 의존성을 추가하지 않아도 됨
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    // Property based testing
    // 속성, 규칙 등이 적합한지 테스트할 때 사용 (예를 들어 입력값을 무작위로 테스트하는 경우)
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}
