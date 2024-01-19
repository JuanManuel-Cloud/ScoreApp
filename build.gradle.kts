// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val applicationVersion = "8.2.1"
    val kotlinVersion = "1.9.22"
//    val kspVersion = "1.9.22-1.0.17"
    val hiltVersion = "2.44"
    val kaptVersion = "2.0.0-Beta3"

    id("com.android.application") version applicationVersion apply false
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
//    id("com.google.devtools.ksp") version kspVersion apply false
    id("com.google.dagger.hilt.android") version hiltVersion apply false
    id("org.jetbrains.kotlin.kapt") version kaptVersion apply false
}