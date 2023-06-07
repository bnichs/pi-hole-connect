import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.tien.piholeconnect"

    compileSdk = 33

    defaultConfig {
        applicationId = "com.tien.piholeconnect"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "SNAPSHOT"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resourceConfigurations += listOf("en", "de")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            ndk {
                debugSymbolLevel = "FULL"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn", "-opt-in=kotlin.time.ExperimentalTime"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${libs.versions.protoBufJavaLite.get()}"
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("java") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(libs.android.billingclient.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.bundles.camerax)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.ktor)
    implementation(libs.google.android.material)
    implementation(libs.google.dagger.hilt)
    implementation(libs.google.protobuf.javalite)
    implementation(libs.google.mlkit.barcodeScanning)
    implementation(libs.philJay.mpAndroidChart)
    implementation("androidx.compose.material3:material3:1.0.0-alpha08")

    kapt(libs.google.dagger.hilt.compiler)

    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.bundles.androidTest)
}

kapt {
    correctErrorTypes = true
}
