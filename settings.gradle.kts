pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("plugin.serialization") version "1.9.25"
        kotlin("jvm") version "2.0.0"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
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

// Baselineprofile
include(":modules:baselineprofile")

// Dev tools
include(":modules:dev_tools")
project(":modules:dev_tools").projectDir = file("modules/dev_tools")

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
include(":modules:base:resources")
include(":modules:base:infrastructure")

project(":modules:base:ui_kit").projectDir = file("modules/base/ui_kit")
project(":modules:base:ui_model").projectDir = file("modules/base/ui_model")
project(":modules:base:resources").projectDir = file("modules/base/resources")
project(":modules:base:infrastructure").projectDir = file("modules/base/infrastructure")

// Features: Scanner (API + Impl)
include(":modules:features:scanner:scanner_api")
include(":modules:features:scanner:scanner_impl")

project(":modules:features:scanner:scanner_api").projectDir = file("modules/features/scanner/scanner_api")
project(":modules:features:scanner:scanner_impl").projectDir = file("modules/features/scanner/scanner_impl")

// Features: History of scans (API + Impl)
include(":modules:features:history_of_scans:history_of_scans_api")
include(":modules:features:history_of_scans:history_of_scans_impl")

project(":modules:features:history_of_scans:history_of_scans_api").projectDir = file("modules/features/history_of_scans/history_of_scans_api")
project(":modules:features:history_of_scans:history_of_scans_impl").projectDir = file("modules/features/history_of_scans/history_of_scans_impl")

// Features: History of carts (API + Impl)
include(":modules:features:history_of_carts:history_of_carts_api")
include(":modules:features:history_of_carts:history_of_carts_impl")

project(":modules:features:history_of_carts:history_of_carts_api").projectDir = file("modules/features/history_of_carts/history_of_carts_api")
project(":modules:features:history_of_carts:history_of_carts_impl").projectDir = file("modules/features/history_of_carts/history_of_carts_impl")

// Features: Cart (API + Impl)
include(":modules:features:cart:cart_api")
include(":modules:features:cart:cart_impl")

project(":modules:features:cart:cart_api").projectDir = file("modules/features/cart/cart_api")
project(":modules:features:cart:cart_impl").projectDir = file("modules/features/cart/cart_impl")

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

