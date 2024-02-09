import com.example.ontopapp.buildsrc.Libs
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.ontopapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ontopapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.ontopapp.di.HiltTestRunner"

        try {
            Properties().apply {
                load(FileInputStream(project.rootProject.file("local.properties")))
                buildConfigField("String", "API_KEY", "\"${this.getProperty("API_KEY")}\"")
            }
        } catch (e: Exception) {
            throw GradleException("Error loading the local.properties: ${e.message}")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.material)
    implementation(Libs.AndroidX.constraintLayout)

    implementation(Libs.Kotlin.reflect)
    implementation(Libs.AndroidX.Navigation.fragmentKtx)
    implementation(Libs.AndroidX.Navigation.uiKtx)

    implementation(Libs.AndroidX.swipeRefresh)

    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)
    implementation(Libs.AndroidX.Lifecycle.runtimeKtx)

    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.compiler)

    implementation(Libs.OkHttp3.loginInterceptor)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.converterGson)

    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)

    implementation(Libs.Arrow.core)

    implementation(Libs.Glide.glide)

    testImplementation(project(":testShared"))
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.turbine)

    androidTestImplementation(project(":appTestShared"))
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.core)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.contrib)
    androidTestImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.Hilt.test)
    androidTestImplementation(Libs.Kotlin.Coroutines.test)
    kaptAndroidTest(Libs.Hilt.compiler)
}
