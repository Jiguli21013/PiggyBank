plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.yanchelenko.piggybank.modules.dev_tools"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    defaultConfig {
        minSdk = 26
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures { compose = true }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation(project(":modules:core:core_api"))

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    // ---- Compose: только ТИПЫ для компиляции (release тоже видит) ----
    compileOnly(platform(libs.compose.bom))
    compileOnly(libs.androidx.compose.runtime) // @Composable
    compileOnly(libs.compose.ui)               // Modifier, LocalContext  ← ВАЖНО

    // ---- Полный рантайм только для debug ----
    debugImplementation(platform(libs.compose.bom))
    debugImplementation(libs.androidx.compose.runtime)
    debugImplementation(libs.compose.ui)
    debugImplementation(libs.compose.material)
    debugImplementation(libs.lifecycle.runtime.compose)

    // Rebugger: API виден всегда, реализация только в debug
    compileOnly(libs.rebugger)
    debugApi(libs.rebugger)
}