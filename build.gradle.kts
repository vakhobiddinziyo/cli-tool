import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.collections.plus

plugins {
    kotlin("jvm") version "2.1.0"
    application
}


tasks.jar {
    manifest {
        attributes["Main-Class"] = "zeroOne/MainKt"
    }
}

application {
    mainClass.set("zeroOne/MainKt")
}


group = "zeroOne"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
    testImplementation(kotlin("test"))
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

kotlin {
    jvmToolchain(17)
}