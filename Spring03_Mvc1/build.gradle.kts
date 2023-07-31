plugins {
	war
	kotlin("jvm")
	kotlin("plugin.spring")

	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "com.jaenyeong"
version = "1.0.0"

dependencies {
	val kotestVersion: String by project

	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// `providedCompile`, `providedRuntime`은 해당 의존성이 `War` 패키징에 포함되지 않게 할 때 사용
	// 의존성 체인을 막을 때 (의존 관계 전이성 방지) 의존성 뒤에 `@jar`를 명시적으로 사용하여 지정할 수 있음
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest:kotest-runner-junit5:${kotestVersion}")
	testImplementation("io.kotest:kotest-property:${kotestVersion}")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}
