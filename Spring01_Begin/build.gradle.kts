import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1" // 스프링 부트
    id("io.spring.dependency-management") version "1.1.1" // 스프링 의존성 관리

    val kotlinVersion = "1.9.0"
    kotlin("jvm") version kotlinVersion // 코틀린 코드를 Java 바이트코드로 컴파일할 때 사용
    kotlin("plugin.spring") version kotlinVersion // 코틀린 스프링 지원 (@Autowired 없는 생성자 주입, null-safety 등)
}

// 순수 코틀린 프로젝트에서는 무관한 설정이나
// Java 코드가 섞여 있거나 사용하는 Java 라이브러리가 특정 JVM 버전을 요구할 수 있기 때문에 설정
java.sourceCompatibility = JavaVersion.VERSION_17
group = "com.jaenyeong"
version = "1.0.0"

val kotestVersion = "5.6.2"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
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
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

// 각 `Test` 태스크가 실제로 필요한 시점에 설정이 적용됨
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

// configuration phase에서 `Test` 태스크를 찾아서 바로 적용
// 구성 단계(configuration phase)에서 모든 태스크를 찾아야 하므로 큰 프로젝트일수록 성능적으로 느릴 수 있음
//tasks.withType<Test> {
//    useJUnitPlatform()
//}
