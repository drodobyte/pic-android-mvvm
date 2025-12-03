plugins {
    id("lib")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.drodobyte.feature.intake"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":domain"))
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
