plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.yanchelenko.piggybank.core.factory"
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
    implementation(project(":features:infrastructure"))

    implementation(project(":features:database"))
    implementation(project(":core:api"))
    implementation(project(":core:impl"))


    implementation(libs.dagger.hilt.android)

    kapt(libs.dagger.hilt.compiler)

    implementation(libs.androidx.room.ktx)
}