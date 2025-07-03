plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "com.yanchelenko.piggybank.modules.core.database"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

room {
    schemaDirectory("${rootProject.projectDir}/schemas")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.paging.runtime) // Pager, CachedPagingData
    implementation(libs.room.paging)
    implementation(libs.play.services.analytics.impl)
    implementation(project(":modules:core:core_api")) // RoomPagingSource
    
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation(libs.kotlinx.datetime)
}
