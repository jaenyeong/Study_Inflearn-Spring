import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.0"
    id("org.springframework.boot") version "3.1.1" // 스프링 부트
    id("io.spring.dependency-management") version "1.1.1" // 스프링 의존성 관리

    kotlin("jvm") version kotlinVersion // 코틀린 코드를 Java 바이트코드로 컴파일할 때 사용
    kotlin("plugin.spring") version kotlinVersion // 코틀린 스프링 지원 (@Autowired 없는 생성자 주입, null-safety 등)
}

// 순수 코틀린 프로젝트에서는 무관한 설정이나
// Java 코드가 섞여 있거나 사용하는 Java 라이브러리가 특정 JVM 버전을 요구할 수 있기 때문에 설정
java.sourceCompatibility = JavaVersion.VERSION_17
group = "com.jaenyeong"
version = "1.0.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
