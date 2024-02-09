

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(com.example.ontopapp.buildsrc.Libs.androidGradlePlugin)
        classpath(com.example.ontopapp.buildsrc.Libs.Kotlin.gradlePlugin)
        classpath(com.example.ontopapp.buildsrc.Libs.navigationGradlePlugin)
        classpath(com.example.ontopapp.buildsrc.Libs.Hilt.gradlePlugin)
        classpath(com.example.ontopapp.buildsrc.Libs.gradleVersionsPlugin)
    }
}
