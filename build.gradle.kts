import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.detekt)
}

val projectSource = file(projectDir)
val configFile = files("$rootDir/config/detekt/detekt.yml")
val kotlinFiles = "**/*.kt"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"
tasks.register<Detekt>("detektAll") {
    description = "Runs a custom detekt build."

    ignoreFailures = true
    buildUponDefaultConfig = true
    setSource(projectSource)
    config.setFrom(configFile)
    include(kotlinFiles)
    exclude(resourceFiles, buildFiles)
    basePath = rootProject.projectDir.absolutePath
}
