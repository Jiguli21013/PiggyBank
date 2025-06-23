plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {

    // Утилиты, расширения и базовые вещи
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // kotlinx.serialization
    implementation(libs.serialization.json)
    implementation(libs.kotlinx.datetime)
}
