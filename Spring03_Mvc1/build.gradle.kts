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

	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest:kotest-runner-junit5:${kotestVersion}")
	testImplementation("io.kotest:kotest-property:${kotestVersion}")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}
