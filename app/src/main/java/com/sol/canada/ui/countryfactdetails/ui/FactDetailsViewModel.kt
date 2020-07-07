package com.sol.canada.ui.countryfactdetails.ui

import androidx.lifecycle.*
import com.sol.canada.data.FactRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

//Shared view model for both activity and fragment
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

    fun refreshItem() {
        _refreshItems.value = true
    }

    //check for db value if count 0  fetch from network and store it in db ( return from db )
    fun getErrorMessage(): LiveData<String> = errorMessage
    fun clearData() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.clearFactsData()
        }
    }

    private fun onError(exception: Throwable) {
        exception.message?.let {
            errorMessage.postValue(exception.message)
        }
    }
}


