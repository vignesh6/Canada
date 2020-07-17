package com.sol.canada.ui

import android.os.Build
import com.facebook.stetho.Stetho
import com.sol.canada.BuildConfig
import com.sol.canada.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * Application class which injects dependency and third party library such as Stetho and Timber
 */
open class CountryFactApp:DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    if (!isRoboUnitTest()) {
        Stetho.initializeWithDefaults(this)
    }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }
    fun isRoboUnitTest(): Boolean {
        return "robolectric" == Build.FINGERPRINT
    }
}