pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        id("org.jetbrains.compose") version "0.4.0"
        kotlin("jvm") version "1.5.10"
    }
}

rootProject.name = "compose-calc"
