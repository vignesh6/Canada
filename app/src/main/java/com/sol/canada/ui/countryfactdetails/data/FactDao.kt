package com.sol.canada.ui.countryfactdetails.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Dao for facts table
 */
@Dao
interface FactDao {

    @Query("SELECT * FROM fact")
    fun getFacts():LiveData<List<FactDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(facts: List<FactDetail>)


    @Transaction
    suspend fun updateData(facts: List<FactDetail>) {
        clearData()
        insertAll(facts)
    }
    @Query("DELETE FROM fact")
    suspend fun clearData()
}