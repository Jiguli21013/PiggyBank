plugins {
    id("com.android.library")
}

android {
    namespace = "com.yanchelenko.piggybank.modules.core.core_api"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {

    implementation(libs.kotlinx.datetime)
    implementation(libs.paging.common)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.hilt.core)
    implementation(project(":modules:base:infrastructure"))
}