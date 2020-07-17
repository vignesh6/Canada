package com.sol.canada.di

import com.sol.canada.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Provides dependency for MainActivity
 */
@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun bindMainActivity():MainActivity
}