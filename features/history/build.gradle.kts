plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)

    kotlin("plugin.serialization")
}

android {
    namespace = "com.yanchelenko.piggybank.features.history"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(project(":common:ui_models_android"))
    implementation(project(":common:core_utils"))
    implementation(project(":common:mappers"))
    implementation(project(":common:extensions"))
    implementation(project(":common:ui_state"))
    implementation(project(":common:ui"))

    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))

    debugImplementation(project(":core:debugUI"))
    debugImplementation(project(":common:ui_preview"))

    implementation(libs.kotlinx.immutable)


    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.androidx.ui.tooling.preview.android)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.navigation.runtime.android)

    // paging
    implementation(libs.paging.compose)

    debugImplementation(libs.ui.tooling)

    kapt(libs.dagger.hilt.compiler)

    implementation(libs.serialization.json)
    implementation(libs.kotlinx.datetime)

}