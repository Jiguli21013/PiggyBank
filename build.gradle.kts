plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.ksp) apply false

    alias(libs.plugins.androidx.baselineprofile) apply false
}

aalekh {

    rules {
        // The production dependency graph must always remain a DAG.
        rule("no-cyclic-dependencies") {
            preventRegression = true
        }
    }

    // -------------------------------------------------------------------------

    // Feature boundaries

    // -------------------------------------------------------------------------

    // Public feature APIs must never know about feature implementations.
    forbid {
        from(":modules:features:**:*_api")
        to(":modules:features:**:*_impl")
        because("feature API modules must not depend on implementation modules")
    }

    // Feature implementations communicate with other features through API only.

    forbid {
        from(":modules:features:**:*_impl")
        to(":modules:features:**:*_impl")
        because("feature implementations must communicate through feature API modules")
    }

    // -------------------------------------------------------------------------

    // Base boundaries

    // -------------------------------------------------------------------------

    // Base is lower-level shared infrastructure and must not know about features.
    forbid {
        from(":modules:base:*")
        to(":modules:features:**")
        because("base modules must not depend on feature modules")
    }

    // Base must not know about the application composition root.
    forbid {
        from(":modules:base:*")
        to(":app")
        because("base modules must not depend on the application module")
    }

    // -------------------------------------------------------------------------

    // Core boundaries

    // -------------------------------------------------------------------------

    // Core must not remain dependent from product features.
    forbid {
        from(":modules:core:*")
        to(":modules:features:**")
        because("core modules must not depend on feature modules")
    }

    // Core must not depend on the application composition root.
    forbid {
        from(":modules:core:*")
        to(":app")
        because("core modules must not depend on the application module")
    }

    // core_api is the public contract of Core.
    forbid {
        from(":modules:core:core_api")
        to(":modules:core:core_impl")
        because("core_api must not depend on its implementation")
    }

    forbid {
        from(":modules:core:core_api")
        to(":modules:core:core_factory")
        because("core_api must not depend on the composition factory")
    }

    forbid {
        from(":modules:core:core_api")
        to(":modules:core:database")
        because("core_api must not depend on database implementation details")
    }

    // -------------------------------------------------------------------------

    // Application boundary

    // -------------------------------------------------------------------------

    // No reusable module may depend on the app composition root.
    forbid {
        from(":modules:features:**")
        to(":app")
        because("feature modules must not depend on the application composition root")
    }

    // -------------------------------------------------------------------------

    // Baseline Profile boundary

    // -------------------------------------------------------------------------

    // Production modules must not depend on benchmark/profile infrastructure.
    forbid {
        from(":modules:base:**")
        to(":modules:baselineprofile")
        because("production base modules must not depend on baseline profile infrastructure")
    }

    forbid {
        from(":modules:core:**")
        to(":modules:baselineprofile")
        because("production core modules must not depend on baseline profile infrastructure")
    }

    forbid {
        from(":modules:features:**")
        to(":modules:baselineprofile")
        because("production feature modules must not depend on baseline profile infrastructure")
    }
}
