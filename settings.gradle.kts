pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        id("org.jetbrains.compose") version "0.2.0-build132"
        kotlin("jvm") version "1.4.20"
    }
}

rootProject.name = "compose-calc"
