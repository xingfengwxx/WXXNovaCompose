plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.wangxingxing.wxxnovacompose.ui.common"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
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
        compose = true
    }
}

dependencies {
    // Core AndroidX libraries
    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(platform(libs.androidx.compose.bom))
    
    // Compose libraries
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    
    // Activity and Fragment
    api(libs.androidx.activity.compose)
    api(libs.androidx.appcompat)
    api(libs.androidx.fragment.ktx)
    
    // Navigation
    api(libs.androidx.navigation.compose)
    
    // Lifecycle
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.viewmodel.compose)
    
    // Paging
    api(libs.androidx.paging.runtime)
    api(libs.androidx.paging.compose)
    
    // Hilt
    api(libs.hilt.android)
    api(libs.hilt.navigation.compose)
    
    // Coroutines
    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)
    
    // Network
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)
    api(libs.gson)
    
    // Database
    api(libs.androidx.room.runtime)
    api(libs.androidx.room.ktx)
    api(libs.androidx.datastore.preferences)
    
    // Image Loading
    api(libs.glide)
    api(libs.glide.compose)
    api(libs.coil)
    api(libs.coil.compose)
    
    // Utilities
    api(libs.blankj.utilcodex)
    api(libs.getactivity.toaster)
}
