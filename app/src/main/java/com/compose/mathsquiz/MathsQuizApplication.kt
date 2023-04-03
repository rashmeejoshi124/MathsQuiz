package com.compose.mathsquiz

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MathsQuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}