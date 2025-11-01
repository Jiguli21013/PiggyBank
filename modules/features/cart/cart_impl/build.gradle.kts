plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)

    kotlin("plugin.serialization")
}

android {
    namespace = "com.yanchelenko.piggybank.modules.features.cart.cart_impl"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    // Project modules
    implementation(project(":modules:core:core_api"))

    implementation(project(":modules:base:ui_kit"))
    implementation(project(":modules:base:ui_model"))
    implementation(project(":modules:base:resources"))
    implementation(project(":modules:base:infrastructure"))

    implementation(project(":modules:features:cart:cart_api"))

    // Compose core
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.foundation.layout.android)

    // Lifecycle / ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Navigation
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.hilt.navigation.compose)

    // DI (Hilt)
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    // Paging
    implementation(libs.paging.compose)

    // Serialization / DateTime
    implementation(libs.serialization.json)
    implementation(libs.kotlinx.datetime)

    // Debug-only tooling
    debugImplementation(project(":modules:dev_tools"))
    debugImplementation(libs.androidx.ui.tooling.preview.android) // Compose Preview in IDE
    debugImplementation(libs.ui.tooling) // Runtime UI inspection & Layout Inspector
}
