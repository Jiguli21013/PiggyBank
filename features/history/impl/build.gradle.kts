plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)

    kotlin("plugin.serialization")
}

android {
    namespace = "com.yanchelenko.piggybank.features.history.impl"
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

    implementation(project(":ui_kit"))
    implementation(project(":features:infrastructure"))

    implementation(project(":core:api"))

    implementation(project(":features:history:api"))

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.lifecycle.runtime.compose)
    //todo debug
    implementation(libs.androidx.ui.tooling.preview.android)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.navigation.runtime.android)

    // paging
    implementation(libs.paging.compose)
    implementation(libs.foundation.layout.android)


    debugImplementation(libs.ui.tooling)

    kapt(libs.dagger.hilt.compiler)

    implementation(libs.serialization.json)
    implementation(libs.kotlinx.datetime)

    debugImplementation(libs.rebugger)
}