package com.joseph.exhibition.core.common.initializer

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.tencent.mmkv.MMKV

/**
 * A class that implements the Initializer interface to initialize common components for the app.
 */
class CommonComponentInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        MMKV.initialize(context)
        Firebase.initialize(context)
        FirebaseCrashlytics.getInstance()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
