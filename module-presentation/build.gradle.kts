import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.spring")
}


val jar: Jar by tasks
val bootJar: BootJar by tasks
val jarName = "app.jar"

bootJar.enabled = true
jar.enabled = false

dependencies {
    implementation(project(":module-domain"))
    implementation(project(":module-infrastructure:persistence-db"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.getByName<BootJar>("bootJar") {
    mainClass.set("site.yourevents.YourEventsApplicationKt")
}

tasks.named<BootJar>("bootJar") {
    archiveFileName.set(jarName)

    doLast {
        copy {
            from("build/libs/$jarName")
            into("../build/libs")
        }
    }
}
