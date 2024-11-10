// Top-level build file where you can add configuration options common to all sub-projects/modules.

//dependency to included buildscript NKHL_2
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("androidx.room:room-compiler:2.6.1")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}