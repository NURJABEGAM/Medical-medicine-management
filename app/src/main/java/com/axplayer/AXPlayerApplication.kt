package com.axplayer

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AXPlayerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()
        initFirebase()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
        Timber.d("AX Player initialized")
    }

    private fun initFirebase() {
        try {
            FirebaseApp.initializeApp(this)
            Timber.d("Firebase initialized successfully")
        } catch (e: Exception) {
            Timber.e(e, "Failed to initialize Firebase")
        }
    }

    private class ReleaseTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            // Only log errors and warnings in release builds
            if (priority == android.util.Log.ERROR || priority == android.util.Log.WARN) {
                // Send to crash reporting service (Firebase Crashlytics)
                t?.let {
                    // FirebaseCrashlytics.getInstance().recordException(it)
                }
            }
        }
    }
}
