plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(project(":common:ui_models"))
    implementation(project(":core:domain"))

    implementation(libs.kotlinx.datetime)
}
