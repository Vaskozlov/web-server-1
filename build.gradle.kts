plugins {
    id("java")
    id("application")
    id("com.gradleup.shadow") version "8.3.2"
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(files("src/main/resources/libs/fastcgi-lib.jar"))
    implementation("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation(kotlin("stdlib-jdk8"))
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.example.Main")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("server.jar")
    configurations = listOf(project.configurations.runtimeClasspath.get())
}

kotlin {
    jvmToolchain(21)
}