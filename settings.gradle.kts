pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("plugin.serialization") version "1.8.10" // Версия Kotlin — должна совпадать с остальными
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "PiggyBank"

include(":app")

// core
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:ui")
include(":core:debugUI")
include(":core:navigation")
include(":core:permissions")

// features
include(":features:scanner")
include(":features:history")
include(":features:product_details")
include(":features:product_insert")
include(":features:product_edit")

// di
include(":di")

// common
include(":common:core_utils")
project(":common:core_utils").projectDir = file("common/core_utils")

include(":common:ui_models")
project(":common:ui_models").projectDir = file("common/ui_models")

include(":common:mappers")
project(":common:mappers").projectDir = file("common/mappers")

include(":common:extensions")
project(":common:extensions").projectDir = file("common/extensions")

