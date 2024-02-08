plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":domain"))

    // JavaInject
    implementation("javax.inject:javax.inject:1")

    // Coroutines Core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Arrow
    implementation("io.arrow-kt:arrow-core:1.1.5")
}