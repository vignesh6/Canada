package com.sol.canada.di

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.sol.canada.util.AppConstant
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides SharedPreference dependency
 */
@SuppressLint("JvmStaticProvidesInObjectDetector")
@Module
object SharedPreferenceModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.applicationContext.getSharedPreferences(
            AppConstant.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }
}