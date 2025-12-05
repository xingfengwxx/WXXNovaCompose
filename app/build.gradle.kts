plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.wangxingxing.wxxnovacompose"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.wangxingxing.wxxnovacompose"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
        dataBinding = false // 明确禁用 DataBinding，只使用 ViewBinding
    }

    // Room schema export (可选，用于导出数据库 schema)
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    
    // Core modules
    implementation(project(":core"))
    implementation(project(":core-di"))
    implementation(project(":core-network"))
    implementation(project(":core-db"))
    implementation(project(":core-models"))
    implementation(project(":core-logging"))
    
    // UI common module
    implementation(project(":ui-common"))
    
    // Feature modules
    implementation(project(":feature-home"))
    implementation(project(":feature-projectcategory"))
}