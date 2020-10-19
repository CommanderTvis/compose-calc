import org.jetbrains.compose.compose

plugins {
    application
    id("org.jetbrains.compose")
    kotlin(module = "jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven(url = "https://dl.bintray.com/kotlin/kotlinx")
    maven(url = "https://dl.bintray.com/hotkeytlt/maven")
    maven(url = "https://dl.bintray.com/mipt-npm/scientifik/")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenCentral()
}

dependencies {
    implementation("kscience.kmath:kmath-ast:0.1.4")
    implementation(compose.desktop.all)
}

application.mainClassName = "io.github.commandertvis.calc.AppKt"
