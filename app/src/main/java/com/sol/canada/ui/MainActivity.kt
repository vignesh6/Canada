package com.sol.canada.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.sol.canada.R
import com.sol.canada.databinding.ActivityMainBinding
import com.sol.canada.ui.countryfactdetails.ui.FactDetailsViewModel
import dagger.android.AndroidInjector
import dagger.android.DaggerActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * Activity which gets invoked on launch of the application
 */
class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel:FactDetailsViewModel

    /**
     * Initialize FactDetailsViewModel, ActivityMainBinding and updates app tittle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main)
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this,viewModelFactory).get(FactDetailsViewModel::class.java)
        navController = findNavController(R.id.nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        //Observing tittle from view model set by FactDetailsFragment
        viewModel.getTittle().observe(this, Observer {
            if(it.toString().isNotEmpty())
            supportActionBar!!.title = it
        })
    }
}