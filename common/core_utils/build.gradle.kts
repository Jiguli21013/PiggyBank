plugins {
    kotlin("jvm")
}

dependencies {
    // Тут обычно утилиты, расширения и базовые штуки
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Логи, ошибки, расширения
    //implementation(libs.timber) // для логирования
}