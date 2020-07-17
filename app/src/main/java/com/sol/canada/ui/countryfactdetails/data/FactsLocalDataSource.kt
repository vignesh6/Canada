package com.sol.canada.ui.countryfactdetails.data

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class responsible for handling all local database operations
 */
@Singleton
class FactsLocalDataSource @Inject constructor(private val dao: FactDao) {

    /**
     * Gets facts detail from FactDao and returns facts
     */
    fun getFacts(): LiveData<List<FactDetail>> {
       return dao.getFacts()
    }

    /**
     * Inserts facts details to Fact table
     */
    suspend fun insertData(rows: List<FactDetail>) {
        dao.updateData(rows)
    }

    /**
     * Clear Fact data from Fact table
     */
    suspend fun clearData() {
        dao.clearData()
    }
}