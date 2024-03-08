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
include(":androidApp:feature-tabs")
include(":androidApp:feature-vod-details")
include(":shared")
include(":shared:feature-auth")
include(":shared:feature-lk")