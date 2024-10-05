plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.itmo"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":dal"))
    implementation(project(":services"))
    implementation(project(":controllers"))

    implementation("org.springframework.boot:spring-boot-starter-web:3.3.0")
    implementation("org.springframework.security:spring-security-core:6.2.4")
    implementation("org.springframework.security:spring-security-config:6.2.4")
    implementation("org.springframework.security:spring-security-web:6.2.4")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
