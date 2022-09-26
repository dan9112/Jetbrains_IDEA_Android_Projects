@file:Suppress("UnstableApiUsage")

import org.gradle.api.JavaVersion.VERSION_11

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.jetbrains_idea_android_projects"
        minSdk = 21
        targetSdk = 32
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = VERSION_11
        targetCompatibility = VERSION_11
    }
    kotlinOptions {
        jvmTarget = VERSION_11.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    val navVersion = "2.5.2"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")

    val fragmentVersion = "1.5.3"
    implementation ("androidx.fragment:fragment-ktx:$fragmentVersion")
    debugImplementation ("androidx.fragment:fragment-testing:$fragmentVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    val truthVersion = "1.1.3"
    testImplementation("com.google.truth:truth:$truthVersion")
    androidTestImplementation("com.google.truth:truth:$truthVersion")

    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
}
