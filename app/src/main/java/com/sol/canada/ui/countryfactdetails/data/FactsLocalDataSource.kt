package com.sol.canada.ui.countryfactdetails.data

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FactsLocalDataSource @Inject constructor(private val dao: FactDao) {

    fun getFacts(): LiveData<List<FactDetail>> {
       return dao.getFacts()
    }

    suspend fun updateData(rows: List<FactDetail>) {
        dao.updateData(rows)
    }

    suspend fun clearData() {
        dao.clearData()
    }
}