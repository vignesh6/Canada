package com.sol.canada.ui.countryfactdetails.ui

import androidx.lifecycle.*
import com.sol.canada.data.FactRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel which is responsible for getting facts details from repository
 *
 * @param repository Repository that return fact details and updated data on refresh
 */

class FactDetailsViewModel @Inject constructor(
    private val repository: FactRepository
) : ViewModel() {
    private val appTittle: MutableLiveData<String> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()
    private val _refreshItems = MutableLiveData<Boolean>()

    init {
        _refreshItems.value = false
    }

    //Tittle observed from activity
    fun getTittle(): LiveData<String> = appTittle

    //Tittle string set from fragment
    fun setTittle(tittle: String) {
        appTittle.postValue(tittle)
    }

    //Get fact details from repository
    val facts = Transformations.switchMap(_refreshItems) {
        repository.getFacts()
    }

    /**
     * This method gets called from FactDetailFragment whenever swipe
     * to refresh is invoked to refresh facts data
     */
    fun refreshItem() {
        _refreshItems.value = true
    }

    //check for db value if count 0  fetch from network and store it in db ( return from db )
    fun getErrorMessage(): LiveData<String> = errorMessage

    /**
     * Clear the facts data when SwipeToRefresh is invoked
     */
    fun clearData() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.clearFactsData()
        }
    }

    /**
     * Handle error message and update the UI observer
     */
    private fun onError(exception: Throwable) {
        exception.message?.let {
            errorMessage.postValue(exception.message)
        }
    }
}


