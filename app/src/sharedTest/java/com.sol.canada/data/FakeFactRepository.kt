package com.sol.canada.data

import androidx.lifecycle.LiveData
import com.sol.canada.ui.countryfactdetails.data.FactDetail
import com.sol.canada.util.TestUtil

class FakeFactRepository:FactRepository {
    override fun getFacts(): LiveData<Result<List<FactDetail>>> {
       return TestUtil.getFactsDetail()
    }

    override suspend fun clearFactsData() {

    }

    override suspend fun updateData(facts: List<FactDetail>) {

    }
}