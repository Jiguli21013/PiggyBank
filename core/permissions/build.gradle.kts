plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
}

android {
    namespace = "com.yanchelenko.permissions"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles("consumer-rules.pro")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    // Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.runtime.android)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.androidx.appcompat)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.androidx.compose.runtime)

    //    implementation(libs.androidx.core.ktx)
}