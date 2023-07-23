import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // 코틀린 코드를 Java 바이트코드로 컴파일할 때 사용
    kotlin("jvm") version "1.9.0" apply false
    // 코틀린 스프링 지원 (@Autowired 없는 생성자 주입, null-safety 등)
    kotlin("plugin.spring") version "1.9.0" apply false
    kotlin("plugin.jpa") version "1.9.0" apply false

    id("io.spring.dependency-management") version "1.1.1" apply false
    id("org.springframework.boot") version "3.1.1" apply false
}

allprojects {
    extra["kotestVersion"] = "5.6.2"

    repositories {
        mavenCentral()
    }
}

subprojects {
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
//    tasks.withType<Test> {
//        useJUnitPlatform()
//    }
}
