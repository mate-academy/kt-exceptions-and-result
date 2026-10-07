plugins {
    kotlin("jvm") version "1.9.20"
    application
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
}

group = "mate.academy"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("mate.academy.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
}
