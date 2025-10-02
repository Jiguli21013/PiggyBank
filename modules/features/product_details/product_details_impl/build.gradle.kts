plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)

    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.yanchelenko.piggybank.modules.features.product_details.product_details_impl"
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
    implementation(project(":modules:features:product_details:product_details_api"))


    implementation(project(":modules:base:ui_kit"))
    implementation(project(":modules:base:infrastructure"))
    implementation(project(":modules:base:ui_model"))

    implementation(project(":modules:core:core_api"))




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
    //todo    debugImplementation ?
    implementation(libs.androidx.benchmark.common)


    ksp(libs.dagger.hilt.compiler)
    implementation(libs.kotlinx.datetime)

    debugImplementation(libs.rebugger)

}