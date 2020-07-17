package com.sol.canada.ui.countryfactdetails.data

import com.sol.canada.data.AppSharedPreference
import com.sol.canada.data.FactRepository
import com.sol.canada.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

const val TITTLE = "tittle"

/**
 * Repository class which handles data from local and remote data source
 * @param localDataSource loacal data source class for database related operation
 * @param remoteDataSource class which handles remote api response
 * @param appSharedPreference Helper class to store data in SharePreference
 */
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
                insertData(it.rows)
                appSharedPreference.putStringData(TITTLE, it.title)
            })

    /**
     * Clears fact data from local database
     */
    override suspend fun clearFactsData() {
        localDataSource.clearData()
    }

    /**
     * Inserts fact data to local database
     * @param facts List contains FactDetail
     */
    override suspend fun insertData(facts: List<FactDetail>) {
       localDataSource.insertData(facts)
    }

}