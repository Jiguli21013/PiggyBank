plugins {
    id("com.android.library")
    kotlin("android")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.yanchelenko.piggybank.common.ui_preview"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    debugImplementation(project(":common:core_utils"))
    debugImplementation(project(":common:ui_models_android"))

    debugImplementation(libs.androidx.runtime.android)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.kotlinx.datetime)
}