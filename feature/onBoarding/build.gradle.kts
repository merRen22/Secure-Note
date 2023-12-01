plugins{
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    compileSdk = libs.versions.sdk.compile.get().toInt()

    namespace = "com.challenge.get.onBoarding"

    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get().toString()
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
}

dependencies {

    // module
    implementation(project(":base"))
    implementation(project(":data:repository"))
    implementation(project(":model"))

    // Dependencies
    // Biometric
    implementation(libs.biometric)

    // Compose
    implementation(libs.bundles.compose)

    implementation(libs.livedata.ktx)
    implementation(libs.livedata.compose)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}