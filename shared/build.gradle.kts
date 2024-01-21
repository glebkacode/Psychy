plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinNativeCoroutine)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.mokkery)
}

android {
    namespace = "com.china.psychy"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.kotlin.serialization)
            implementation(libs.kotlin.coroutines)
            implementation(libs.kotlin.inject.runtime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.coroutines.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlin.coroutines.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }

    kotlin.sourceSets.all {
        languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
    }
}

dependencies {
    // KSP will eventually have better multiplatform support and we'll be able to simply have
    // `ksp libs.kotlinInject.compiler` in the dependencies block of each source set
    // https://github.com/google/ksp/pull/1021
    add("kspIosX64", libs.kotlin.inject.ksp)
    add("kspIosArm64", libs.kotlin.inject.ksp)
    add("kspIosSimulatorArm64", libs.kotlin.inject.ksp)
}