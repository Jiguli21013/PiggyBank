plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)

    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.yanchelenko.piggybank"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yanchelenko.piggybank"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        //multiDexEnabled = true // 65к методов
        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":di"))
    
    implementation(project(":features:scanner"))
    implementation(project(":features:history"))
    implementation(project(":features:product_details"))
    implementation(project(":features:product_insert"))
    implementation(project(":features:product_edit"))

    implementation(project(":common:ui_models_android"))
    implementation(project(":common:core_utils"))

    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

    debugImplementation(project(":core:debugUI"))

    //debugImplementation(libs.androidx.ui.tooling)
    //debugImplementation(libs.androidx.ui.test.manifest)

    //implementation(libs.androidx.profileinstaller)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)


    implementation(libs.androidx.material3.android)




    implementation(libs.dagger.hilt.android)


    kapt(libs.dagger.hilt.compiler)

    implementation(libs.serialization.json)
}