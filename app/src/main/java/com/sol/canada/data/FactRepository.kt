package com.sol.canada.data

import androidx.lifecycle.LiveData
import com.sol.canada.ui.countryfactdetails.data.FactDetail

interface FactRepository {
    fun getFacts(): LiveData<Result<List<FactDetail>>>
    suspend fun clearFactsData()
    suspend fun insertData(facts: List<FactDetail>)
}