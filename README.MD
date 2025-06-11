Приложения позволяет пользователю сканировать штрих коды продуктов и сохранить значения в локальную бд.

Технологический стек:
- Android SDK
- [Android Jetpack](https://developer.android.com/jetpack)
- [Kotlin 2.0](https://kotlinlang.org/docs/whatsnew20.html)
- [KotlinX Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization)
- [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/)
- [Jetpack Compose](https://developer.android.com/develop/ui/compose)
- [Dagger](https://dagger.dev) + [Hilt](https://dagger.dev/hilt/)
- [Jetpack Room](https://developer.android.com/jetpack/androidx/releases/room)
- [Material](https://github.com/material-components/material-components-android)

Основные модули
- core:* - базовые модули приложения для работы с данными
- features:* - все фичи приложения
- app - сборка приложения

В планах:
1. пересмотреть зависимости в модулях
2. если на оба айтема нажать одновременно, то навигация дублируется ( на экране истории )
3. покрыть тестами
4. весь текст в ресурсы
5. ночная дневная тема
6. смена языка в рантайме
7. внедрить baseInlineProfiler# PiggyBank
