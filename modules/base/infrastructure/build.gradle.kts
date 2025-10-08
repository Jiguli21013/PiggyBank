plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yanchelenko.piggybank.modules.base.infrastructure"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.datetime)
    implementation(libs.paging.runtime)

    testImplementation(libs.junit.jupiter)
}
