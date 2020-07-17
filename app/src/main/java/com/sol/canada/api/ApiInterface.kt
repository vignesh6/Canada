package com.sol.canada.api

import com.sol.canada.ui.countryfactdetails.data.Fact
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface for remote api
 */
interface ApiInterface {
    @GET("facts.json")
    suspend fun getFacts(): Response<Fact>

}