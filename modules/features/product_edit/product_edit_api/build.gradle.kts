plugins {
    id("com.android.library")
}

android {
    namespace = "com.yanchelenko.piggybank.modules.features.product_edit.product_edit_api"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(project(":modules:core:core_api"))
    implementation(libs.androidx.navigation.compose)
}
