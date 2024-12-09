plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.demoapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.demoapp"
        minSdk = 24
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //Glide
    implementation(libs.glide)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //OkHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation (libs.dagger)
    kapt (libs.dagger.compiler)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.fragment.ktx)

    // Live data
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



}