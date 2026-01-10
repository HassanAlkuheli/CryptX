rootProject.name = "crypto_wallet"
include(":shared")
include(":iosApp")
include(":androidApp:app")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("multiplatform") version "2.3.0"
        kotlin("android") version "2.3.0"
        id("com.android.application") version "8.7.3"
        id("org.jetbrains.kotlin.plugin.compose") version "2.3.0"
    }
}