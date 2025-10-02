plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)

    kotlin("plugin.serialization")
}

android {
    namespace = "com.yanchelenko.piggybank.modules.features.scanner.scanner_impl"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    testOptions {
        unitTests.all {
            it.isEnabled = false
        }
    }

    sourceSets.getByName("androidTest").setRoot("src/disabledAndroidTest")
}

dependencies {
    implementation(project(":modules:features:scanner:scanner_api"))

    implementation(project(":modules:core:core_api"))

    implementation(project(":modules:base:infrastructure"))
    implementation(project(":modules:base:ui_kit"))

    // ML Kit + Camera
    implementation(libs.mlkit.barcode.scanning)
    implementation(libs.camera.mlkit.vision)
    implementation(libs.androidx.camera.core)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.androidx.ui.tooling.preview.android)

    // Permissions //todo vrode ne nado
    implementation(libs.accompanistPermissions)

    // Navigation
    implementation(libs.androidx.navigation.common.android)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.hilt.navigation.compose)

    // DI
    implementation(libs.dagger.hilt.android)


    ksp(libs.dagger.hilt.compiler)

    // Serialization + time
    implementation(libs.serialization.json)
    implementation(libs.kotlinx.datetime)
}
