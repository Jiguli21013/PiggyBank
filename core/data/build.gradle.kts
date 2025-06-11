plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.yanchelenko.piggybank.core.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":common:core_utils"))

    implementation(project(":core:domain"))
    implementation(project(":core:database"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.dagger.hilt.android)

    implementation(libs.paging.runtime) // Pager, CachedPagingData


    kapt(libs.dagger.hilt.compiler)

    //implementation(libs.kotlinx.coroutines.android)



    // Сетевые зависимости
    //implementation(libs.retrofit)
    //implementation(libs.okhttp)
}