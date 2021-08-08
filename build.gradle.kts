import org.jetbrains.compose.compose

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(libs.kmath.ast)
}

application.mainClass.set("io.github.commandertvis.calc.AppKt")
