package com.sol.canada.di

import android.content.Context
import com.sol.canada.ui.CountryFactApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Modules that are injected in to application are provided
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,ViewModelModule::class,ActivityBuildersModule::class,AppModule::class,SharedPreferenceModule::class,NetworkModule::class])
interface ApplicationComponent : AndroidInjector<CountryFactApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}