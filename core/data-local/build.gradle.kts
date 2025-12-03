plugins {
    id("lib")
}

android {
    namespace = "com.drodobyte.core.data.local"
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}
