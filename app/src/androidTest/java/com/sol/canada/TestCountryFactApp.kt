package com.sol.canada

import com.sol.canada.ui.CountryFactApp
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestCountryFactApp {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
}