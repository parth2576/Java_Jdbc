plugins {
    kotlin("jvm") version "1.8.21"  // Ensure you have the Kotlin plugin if you are using Kotlin
    id("java")  // This applies the Java plugin
    id("application")  // Optional: for running a standalone application
}

repositories {
    mavenCentral()  // This tells Gradle to download dependencies from Maven Central
}

dependencies {
    implementation("org.postgresql:postgresql:42.5.1")  // PostgreSQL JDBC driver
    // If you are using Kotlin, add the Kotlin stdlib (optional)
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}
tasks.test {
    useJUnitPlatform()
}