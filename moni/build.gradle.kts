plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.moni.scoreapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.moni.scoreapp"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true // Allow references to generated code
        useBuildCache = true
    }
}

dependencies {
    val androidxCoreVersion = "1.12.0"
    val androidxAppCompatVersion = "1.6.1"
    val constraintLayoutVersion = "2.1.4"
    val materialVersion = "1.11.0"
    val androidXTestVersion = "1.5.0"
    val testRunnerVersion = "1.5.2"
    val testRulesVersion = "1.5.0"
    val truthVersion = "1.2.0"
    val junitVersion = "4.13.2"
    val extJunitVersion = "1.1.5"
    val roomVersion = "2.6.1"
    val retrofitVersion = "2.9.0"
    val hiltVersion = "2.44"

    implementation("androidx.core:core-ktx:$androidxCoreVersion")
    implementation("androidx.appcompat:appcompat:$androidxAppCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")

    // JUnit
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("junit:junit:$junitVersion")

    testImplementation("androidx.test.ext:$extJunitVersion")
    androidTestImplementation("androidx.test.ext:junit:$extJunitVersion")

    // Core library
    androidTestImplementation("androidx.test:core:$androidXTestVersion")

    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation("androidx.test:runner:$testRunnerVersion")
    androidTestImplementation("androidx.test:rules:$testRulesVersion")

    // Assertions
    testImplementation("com.google.truth:truth:$truthVersion")
    androidTestImplementation("com.google.truth:truth:$truthVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
}