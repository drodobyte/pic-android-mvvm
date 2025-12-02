plugins {
    id("common.lib")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.drodobyte.core.data"
}

dependencies {
    implementation(project(":core:data-local"))
    implementation(project(":core:data-remote"))
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
