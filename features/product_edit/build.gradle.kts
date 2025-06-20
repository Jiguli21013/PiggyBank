plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)

    kotlin("plugin.serialization")
}

android {
    namespace = "com.yanchelenko.piggybank.features.product_edit"
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
    implementation(project(":common:ui_models"))
    implementation(project(":common:mappers"))
    implementation(project(":common:extensions"))

    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))

    implementation(libs.kotlinx.immutable)

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.androidx.ui.tooling.preview.android)

    implementation(libs.androidx.navigation.runtime.android)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    implementation(libs.serialization.json)

    // hilt
    implementation(libs.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)
    implementation(project(":common:core_utils"))
    implementation(project(":core:debugUI"))
    implementation(project(":core:debugUI"))
    debugImplementation(libs.ui.tooling)




    kapt(libs.dagger.hilt.compiler)
    implementation(libs.kotlinx.datetime)
}