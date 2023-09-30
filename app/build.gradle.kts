plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    kotlin("kapt")
    id(libs.plugins.hilt.android.get().pluginId)
}

android {
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "com.challenge.get"

        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    hilt { enableAggregatingTask = true }

    buildFeatures { compose = true }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get().toString()
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("/META-INF/LICENSE*")
        }
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // module
    implementation(project(":base"))
    implementation(project(":feature:onBoarding"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:home"))

    // Dependencies
    // Compose
    implementation(libs.bundles.compose)

    // SplashScreen
    implementation(libs.splashscreen)

    // Datastore
    implementation(libs.datastore.android)

    // Security
    implementation(libs.shared.preferences)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava")
}