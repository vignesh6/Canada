package com.sol.canada.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sol.canada.ui.countryfactdetails.ui.FactDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FactDetailsViewModel::class)
    abstract fun bindFactsViewModel(factsViewModel: FactDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}