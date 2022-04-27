import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.kian.yun.sheetshow.sheet"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(project(":sheet-common"))
    testCompileOnly(project(":sheet-common"))
    implementation(project(":sheet-domain"))
    testImplementation(project(":sheet-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("mysql:mysql-connector-java")

    testCompileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("com.h2database:h2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<BootJar>("bootJar") {
    enabled = true
    mainClass.set("com.kian.yun.sheetshow.sheet.rest.SheetRestApplication")
}

tasks.named<Jar>("jar") {
    enabled = false
}