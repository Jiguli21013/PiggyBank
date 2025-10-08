plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.dagger.hilt.android)

    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.baselineprofile)
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

        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // todo проверить какие модули не нужны тут
    implementation(project(":modules:dev_tools"))

    implementation(project(":modules:core:database"))
    implementation(project(":modules:core:core_api"))
    implementation(project(":modules:core:core_impl"))
    implementation(project(":modules:core:core_factory"))

    implementation(project(":modules:base:ui_kit"))
    implementation(project(":modules:base:ui_model"))
    implementation(project(":modules:base:infrastructure"))

    implementation(project(":modules:features:history:history_api"))
    implementation(project(":modules:features:history:history_impl"))

    implementation(project(":modules:features:scanner:scanner_api"))
    implementation(project(":modules:features:scanner:scanner_impl"))

    implementation(project(":modules:features:product_edit:product_edit_api"))
    implementation(project(":modules:features:product_edit:product_edit_impl"))

    implementation(project(":modules:features:product_insert:product_insert_api"))
    implementation(project(":modules:features:product_insert:product_insert_impl"))

    implementation(project(":modules:features:product_details:product_details_api"))
    implementation(project(":modules:features:product_details:product_details_impl"))

    // Baselineprofile
    implementation(libs.profileinstaller)
    baselineProfile(project(":modules:baselineprofile"))


    //debugImplementation(libs.androidx.ui.tooling)
    //debugImplementation(libs.androidx.ui.test.manifest)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)


    implementation(libs.androidx.material3.android)




    implementation(libs.dagger.hilt.android)



    ksp(libs.dagger.hilt.compiler)

    implementation(libs.serialization.json)
}