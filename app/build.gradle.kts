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

        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    implementation(project(":features:database"))
    implementation(project(":core:api"))
    implementation(project(":core:impl"))
    implementation(project(":core:factory"))

    implementation(project(":ui_kit"))
    implementation(project(":features:infrastructure"))

    implementation(project(":features:history:api"))
    implementation(project(":features:history:impl"))

    implementation(project(":features:scanner:api"))
    implementation(project(":features:scanner:impl"))

    implementation(project(":features:product:edit:api"))
    implementation(project(":features:product:edit:impl"))

    implementation(project(":features:product:insert:api"))
    implementation(project(":features:product:insert:impl"))

    implementation(project(":features:product:details:api"))
    implementation(project(":features:product:details:impl"))


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