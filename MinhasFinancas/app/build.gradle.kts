plugins {
    alias(libs.plugins.android.application)
    // Se estiver usando Kotlin, adicione o plugin KAPT aqui
    // id 'kotlin-kapt'
}

android {
    namespace = "com.example.minhasfinancas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.minhasfinancas"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Dependência do Room
    implementation("androidx.room:room-runtime:2.7.1")

    // Se estiver usando Kotlin, use kapt ao invés de annotationProcessor
    // kapt "androidx.room:room-compiler:2.7.1"
    annotationProcessor("androidx.room:room-compiler:2.7.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
