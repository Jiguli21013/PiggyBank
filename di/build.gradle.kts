plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") // Нужно для аннотаций Hilt
    id("dagger.hilt.android.plugin") // Плагин для Hilt
}

android {
    namespace = "com.yanchelenko.piggybank.di"
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
    implementation(project(":core:navigation"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))

    implementation(libs.dagger.hilt.android)
    implementation(project(":common:core_utils"))
    implementation(project(":core:ui"))
    // implementation(libs.androidx.room.runtime.jvm)

    kapt(libs.dagger.hilt.compiler)
    implementation(libs.androidx.room.ktx)
}
