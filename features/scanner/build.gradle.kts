plugins {
    id("com.android.library") // Библиотечный модуль, а не application
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)

    kotlin("plugin.serialization") // Плагин для сериализации
}

android {
    namespace = "com.yanchelenko.piggybank.features.scanner"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

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

    // Отключаем unit-тесты
    testOptions {
        unitTests.all {
            it.isEnabled = false
        }
    }

    // Отключаем androidTest, перенаправляя его в фейковый sourceSet
    sourceSets.getByName("androidTest").setRoot("src/disabledAndroidTest")
}

dependencies {
    implementation(project(":common:core_utils"))
    implementation(project(":common:ui"))

    implementation(project(":core:permissions"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))

    implementation(project(":di"))

    debugImplementation(project(":core:debugUI"))

    // ML Kit Barcode Scanning
    implementation(libs.mlkit.barcode.scanning)
    implementation(libs.camera.mlkit.vision)

    // CameraX dependencies for camera integration
    implementation(libs.androidx.camera.core)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)

    // Accompanist Permissions for handling runtime permissions
    implementation(libs.accompanistPermissions)
    implementation(libs.androidx.runtime.saveable.android)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.ui.geometry.android)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.foundation.android)

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.navigation.common.android)
    implementation(libs.androidx.navigation.runtime.android)

    // hilt
    implementation(libs.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)


    kapt(libs.dagger.hilt.compiler)

    implementation(libs.serialization.json) //todo
    implementation(libs.kotlinx.datetime)
}