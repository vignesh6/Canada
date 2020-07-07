package com.sol.canada.di

import com.sol.canada.ui.countryfactdetails.ui.FactDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeFactDetailsFragment(): FactDetailsFragment
}