package com.sol.canada.ui.countryfactdetails.data

import com.sol.canada.data.AppSharedPreference
import com.sol.canada.data.FactRepository
import com.sol.canada.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

const val TITTLE = "tittle"

@Singleton
class FactDetailsRepository @Inject constructor(
    private val localDataSource: FactsLocalDataSource,
    private val remoteDataSource: FactsRemoteDataSource,
    private val appSharedPreference: AppSharedPreference
) : FactRepository {
    //Uses single source of truth strategy which returns data always from database
    override fun getFacts() =
        resultLiveData(
            databaseQuery = { localDataSource.getFacts() },
            networkCall = { remoteDataSource.fetchFacts() },
            saveCallResult = {
                updateData(it.rows)
                appSharedPreference.putStringData(TITTLE, it.title)
            })

    override suspend fun clearFactsData() {
        localDataSource.clearData()
    }

    override suspend fun updateData(facts: List<FactDetail>) {
       localDataSource.updateData(facts)
    }

}