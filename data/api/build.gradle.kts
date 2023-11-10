plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    kotlin("kapt")
    id(libs.plugins.hilt.android.get().pluginId)
    id(libs.plugins.kotlin.serialization.get().pluginId)
}

android {
    compileSdk = libs.versions.sdk.compile.get().toInt()

    namespace = "com.challenge.get.api"

    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }
}

dependencies {
    implementation(project(":model"))

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // network
    implementation(libs.bundles.network)
}