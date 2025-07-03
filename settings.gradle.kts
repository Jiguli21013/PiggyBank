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
include(":modules:core:core_api")
include(":modules:core:core_impl")
include(":modules:core:core_factory")
include(":modules:core:database")

project(":modules:core:core_api").projectDir = file("modules/core/core_api")
project(":modules:core:core_impl").projectDir = file("modules/core/core_impl")
project(":modules:core:core_factory").projectDir = file("modules/core/core_factory")
project(":modules:core:database").projectDir = file("modules/core/database")

// Base (Reusable infrastructure & UI)
include(":modules:base:ui_kit")
include(":modules:base:ui_model")
include(":modules:base:infrastructure")

project(":modules:base:ui_kit").projectDir = file("modules/base/ui_kit")
project(":modules:base:ui_model").projectDir = file("modules/base/ui_model")
project(":modules:base:infrastructure").projectDir = file("modules/base/infrastructure")

// Features: Scanner (API + Impl)
include(":modules:features:scanner:scanner_api")
include(":modules:features:scanner:scanner_impl")

project(":modules:features:scanner:scanner_api").projectDir = file("modules/features/scanner/scanner_api")
project(":modules:features:scanner:scanner_impl").projectDir = file("modules/features/scanner/scanner_impl")

// Features: History (API + Impl)
include(":modules:features:history:history_api")
include(":modules:features:history:history_impl")

project(":modules:features:history:history_api").projectDir = file("modules/features/history/history_api")
project(":modules:features:history:history_impl").projectDir = file("modules/features/history/history_impl")

// Features: Product Insert (API + Impl)
include(":modules:features:product_insert:product_insert_api")
include(":modules:features:product_insert:product_insert_impl")

project(":modules:features:product_insert:product_insert_api").projectDir = file("modules/features/product_insert/product_insert_api")
project(":modules:features:product_insert:product_insert_impl").projectDir = file("modules/features/product_insert/product_insert_impl")

// Features: Product Edit (API + Impl)
include(":modules:features:product_edit:product_edit_api")
include(":modules:features:product_edit:product_edit_impl")

project(":modules:features:product_edit:product_edit_api").projectDir = file("modules/features/product_edit/product_edit_api")
project(":modules:features:product_edit:product_edit_impl").projectDir = file("modules/features/product_edit/product_edit_impl")

// Features: Product Details (API + Impl)
include(":modules:features:product_details:product_details_api")
include(":modules:features:product_details:product_details_impl")

project(":modules:features:product_details:product_details_api").projectDir = file("modules/features/product_details/product_details_api")
project(":modules:features:product_details:product_details_impl").projectDir = file("modules/features/product_details/product_details_impl")

