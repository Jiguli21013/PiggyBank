plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlin.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":common:core_utils"))

    implementation(libs.serialization.json)
    implementation(libs.kotlinx.datetime)
}
