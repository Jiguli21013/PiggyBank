plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)

    kotlin("plugin.serialization")
}

android {
    namespace = "com.yanchelenko.piggybank.features.product.insert.impl"
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
    implementation(project(":features:product:insert:api"))

    implementation(project(":core:api"))

    implementation(project(":ui_kit"))
    implementation(project(":features:infrastructure"))

    debugImplementation(libs.ui.tooling)

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

    kapt(libs.dagger.hilt.compiler)
    implementation(libs.kotlinx.datetime)

    debugImplementation(libs.rebugger)
}
