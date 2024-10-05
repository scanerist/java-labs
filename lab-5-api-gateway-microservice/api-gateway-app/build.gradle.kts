plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
}

apply(plugin = "io.spring.dependency-management")

group = "ru.itmo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":cats-microservice:cats-clients"))
    implementation(project(":cat-owners-microservice:cat-owners-clients"))
    implementation(project(":api-gateway-microservice:api-gateway-models"))
    implementation(project(":api-gateway-microservice:api-gateway-kafka"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    implementation("org.postgresql:postgresql:42.7.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}