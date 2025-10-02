plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.yanchelenko.piggybank.modules.core.core_factory"
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
    implementation(project(":modules:base:infrastructure"))

    implementation(project(":modules:core:database"))
    implementation(project(":modules:core:core_api"))
    implementation(project(":modules:core:core_impl"))


    implementation(libs.dagger.hilt.android)

    ksp(libs.dagger.hilt.compiler)

    implementation(libs.androidx.room.ktx)
}