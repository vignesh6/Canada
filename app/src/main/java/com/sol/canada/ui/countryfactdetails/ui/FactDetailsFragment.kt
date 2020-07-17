package com.sol.canada.ui.countryfactdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sol.canada.data.AppSharedPreference
import com.sol.canada.data.Result
import com.sol.canada.databinding.FragmentFactsBinding
import com.sol.canada.ui.countryfactdetails.data.TITTLE
import dagger.android.support.DaggerFragment
import fr.dasilvacampos.network.monitoring.ConnectivityStateHolder
import fr.dasilvacampos.network.monitoring.Event
import fr.dasilvacampos.network.monitoring.NetworkEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import timber.log.Timber
import javax.inject.Inject

const val NETWORK_ERROR = "Check Network Connection and Try Again!"
class FactDetailsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var isConnected = false

    @Inject
    lateinit var appSharedPreference: AppSharedPreference
    private lateinit var factViewModel: FactDetailsViewModel
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            factViewModel =
                ViewModelProvider(it, viewModelFactory).get(FactDetailsViewModel::class.java)
        }
        val binding = FragmentFactsBinding.inflate(inflater, container, false)
        val adapter = FactsDetailAdapter()
        layoutManager = LinearLayoutManager(activity)
        setLayoutManager(binding)
        binding.factsRecyclerview.adapter = adapter
        subscribeUi(binding, adapter)
        return binding.root
    }

    private fun setLayoutManager(binding: @NotNull FragmentFactsBinding) {
        val recyclerView = binding.factsRecyclerview
        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.scrollToPosition(scrollPosition)
    }

    private fun subscribeUi(
        binding: FragmentFactsBinding,
        adapter: FactsDetailAdapter
    ) {
        observeFacts(binding, adapter)
        binding.refreshFacts.setOnRefreshListener {
            factViewModel.clearData()
            adapter.submitList(null)
            factViewModel.refreshItem()
        }
        factViewModel.getErrorMessage().observe(viewLifecycleOwner, Observer {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        })
        NetworkEvents.observe(viewLifecycleOwner, Observer {
            if (it is Event.ConnectivityEvent)
                handleConnectivityChange()
        })
        binding.retryButton.setOnClickListener {
            factViewModel.refreshItem()
        }
    }

    private fun observeFacts(
        binding: FragmentFactsBinding,
        adapter: FactsDetailAdapter
    ) {
        factViewModel.facts.observe(viewLifecycleOwner, Observer { result ->
            if (binding.refreshFacts.isRefreshing) {
                binding.refreshFacts.isRefreshing = false
            }
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    result.data?.let { it ->
                        Timber.e("${appSharedPreference.getStringData(TITTLE)!!} ")
                        factViewModel.setTittle(appSharedPreference.getStringData(TITTLE)!!)
                        adapter.submitList(it.filterNot { (it.description == null && it.title == null && it.imageHref == null) })
                    }
                }
                Result.Status.LOADING -> {
                    binding.retryButton.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                Result.Status.ERROR -> {
                    lifecycleScope.launch(Dispatchers.Main) {
                        binding.progressBar.visibility = View.GONE
                    }
                    //Hide Snackbar when loading from local database
                    if (adapter.itemCount == 0) {
                        binding.retryButton.visibility = View.VISIBLE
                        val errorMessage =
                            if (!isConnected)
                                NETWORK_ERROR
                            else
                                result.message!!
                        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun handleConnectivityChange() {
        isConnected = ConnectivityStateHolder.isConnected
    }

}