package com.sol.canada.ui.countryfactdetails.data

import com.sol.canada.api.ApiInterface
import com.sol.canada.api.BaseDataSource
import javax.inject.Inject

class FactsRemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) :
    BaseDataSource() {
    suspend fun fetchFacts() = getResult { apiInterface.getFacts() }
}