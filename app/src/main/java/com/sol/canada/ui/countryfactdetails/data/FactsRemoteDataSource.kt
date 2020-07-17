package com.sol.canada.ui.countryfactdetails.data

import com.sol.canada.api.ApiInterface
import com.sol.canada.api.BaseDataSource
import javax.inject.Inject

/**
 * Class responsible for fetch data from ApiInterface
 */
class FactsRemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) :
    BaseDataSource() {
    /**
     * Fetches facts from rest api using ApiInterface
     */
    suspend fun fetchFacts() = getResult { apiInterface.getFacts() }
}