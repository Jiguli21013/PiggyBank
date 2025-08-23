plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yanchelenko.piggybank.core.api"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.paging.common)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.hilt.core)
    implementation(project(":features:infrastructure"))
}