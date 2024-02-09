plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    // JavaInject
    implementation("javax.inject:javax.inject:1")

    // Coroutines core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    // Arrow
    implementation("io.arrow-kt:arrow-core:1.1.5")
    testImplementation(project(":testShared"))
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.mockito:mockito-inline:4.4.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}
