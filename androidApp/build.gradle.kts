plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.china.psychy.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.china.psychy.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(project(":androidApp:core"))
    implementation(project(":androidApp:feature-auth"))
    implementation(project(":androidApp:feature-tabs"))
    implementation(project(":androidApp:feature-player"))
    implementation(project(":shared:feature-auth"))
    implementation(project(":shared:feature-lk"))
    implementation(libs.decompose)
    implementation(libs.decompose.jetpack)
    implementation(libs.mvikotlin)
    implementation(libs.mvikotlin.main)
    implementation(libs.mvikotlin.logging)
    implementation(libs.mvikotlin.extensions.coroutines)
    implementation(libs.mvikotlin.timetravel)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.androidx.fragment.ktx)
    ksp(libs.kotlin.inject.ksp)
    implementation(libs.kotlin.inject.runtime)
    debugImplementation(libs.compose.ui.tooling)
}