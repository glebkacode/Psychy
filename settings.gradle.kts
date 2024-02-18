enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Psychy"
include(":androidApp")
include(":androidApp:core")
include(":androidApp:feature-auth")
include(":androidApp:feature-lk")
include(":androidApp:feature-profile")
include(":androidApp:feature-sessions")
include(":shared")
include(":shared:feature-auth")
include(":shared:feature-lk")
include(":shared:feature-sessions")
include(":shared:feature-profile")