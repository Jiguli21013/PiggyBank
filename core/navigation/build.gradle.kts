plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") // Плагин для сериализации
}

android {
    namespace = "com.yanchelenko.piggybank.navigation"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
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
    implementation(project(":common:ui_models"))
    implementation(project(":core:ui"))

    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.compose.runtime)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.dagger.hilt.android)
    implementation(libs.serialization.json)
}
