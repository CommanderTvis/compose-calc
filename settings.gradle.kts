pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        id("org.jetbrains.compose") version "0.1.0-dev87"
        kotlin("jvm") version "1.4.10"
    }
}

rootProject.name = "compose-calc"
