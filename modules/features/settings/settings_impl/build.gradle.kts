plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)

    kotlin("plugin.serialization")
}

android {
    namespace = "com.yanchelenko.piggybank.modules.features.settings.settings_impl"
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

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
            // Лог в консоль
            it.testLogging.apply {
                events("passed", "skipped", "failed")
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                showStandardStreams = false
            }
        }
    }

    // Отключаем androidTest, перенаправляя его в фейковый sourceSet
    sourceSets.getByName("androidTest").setRoot("src/disabledAndroidTest")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
//todo пересмотреть что мне тут нужно. а что нет
dependencies {
    implementation(project(":modules:features:settings:settings_api"))

    implementation(project(":modules:core:core_api"))

    implementation(project(":modules:base:ui_kit"))
    implementation(project(":modules:base:ui_model"))
    implementation(project(":modules:base:infrastructure"))

    compileOnly(project(":modules:dev_tools"))

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

    ksp(libs.dagger.hilt.compiler)

    implementation(libs.kotlinx.datetime)

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}
