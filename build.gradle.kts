plugins {
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("org.jetbrains.kotlin.plugin.compose") apply false
}

// Put common build configuration here as needed (repositories, versions, etc.)
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}