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


rootProject.name = "ScanRealPrice"

include(":app")
// Core (API + Impl + Factory)
include(":core:api")
include(":core:impl")
include(":core:factory")
// Base (Reusable infrastructure & UI)
include(":ui_kit")
include(":features:database")
include(":features:infrastructure")
// Features: Scanner (API + Impl)
include(":features:scanner:api")
include(":features:scanner:impl")
// Features: History (API + Impl)
include(":features:history:api")
include(":features:history:impl")
// Features: Product Insert (API + Impl)
include(":features:product:insert:api")
include(":features:product:insert:impl")
// Features: Product Edit (API + Impl)
include(":features:product:edit:api")
include(":features:product:edit:impl")
// Features: Product Details (API + Impl)
include(":features:product:details:api")
include(":features:product:details:impl")
