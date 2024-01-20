plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    val androidxCoreVersion = "1.2.0"
    val androidxAppCompatVersion = "1.6.1"
    val constraintLayoutVersion = "2.1.4"
    val materialVersion = "1.11.0"
    val lifecycleVersion = "2.7.0"
    val lifecycleExtVersion = "2.2.0"
    val corutinesVersion = "1.7.1"
    val androidXTestVersion = "1.5.0"
    val testRunnerVersion = "1.5.2"
    val testRulesVersion = "1.5.0"
    val truthVersion = "1.2.0"
    val junitVersion = "4.13.2"
    val extJunitVersion = "1.1.5"
    val roomVersion = "2.6.1"
    val retrofitVersion = "2.9.0"
    val hiltVersion = "2.48"
    val vmHiltVersion = "1.0.0-alpha03"
    val fragmentVersion = "1.6.2"
    val navigationVersion = "2.7.6"

    implementation("androidx.core:core-ktx:$androidxCoreVersion")
    implementation("androidx.appcompat:appcompat:$androidxAppCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")

    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleExtVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$corutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$corutinesVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

    // Navigation components
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Testing

    // Testing-JUnit
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("junit:junit:$junitVersion")

    testImplementation("androidx.test.ext:junit:$extJunitVersion")
    androidTestImplementation("androidx.test.ext:junit:$extJunitVersion")

    // Testing - Core library
    androidTestImplementation("androidx.test:core:$androidXTestVersion")

    // Testing - AndroidJUnitRunner and JUnit Rules
    androidTestImplementation("androidx.test:runner:$testRunnerVersion")
    androidTestImplementation("androidx.test:rules:$testRulesVersion")

    // Testing - Assertions
    testImplementation("com.google.truth:truth:$truthVersion")
    androidTestImplementation("com.google.truth:truth:$truthVersion")

    // Testing-Hilt
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-compiler:$hiltVersion")
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptTest("com.google.dagger:hilt-compiler:$hiltVersion")
}