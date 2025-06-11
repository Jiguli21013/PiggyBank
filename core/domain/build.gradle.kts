plugins {
    kotlin("jvm")
}

dependencies {

    implementation(project(":common:core_utils"))
    implementation(project(":common:extensions"))

    implementation(libs.javax.inject)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.datetime)

    implementation(libs.paging.common)


    // Domain — обычно без зависимостей или только на Kotlin
}


