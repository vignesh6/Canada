package com.sol.canada.api

import com.sol.canada.ui.countryfactdetails.data.Fact
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    companion object{
        const val API_END_POINT_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
        const val API_TEST_END_POINT_URL = "http://localhost:8080/"
    }
    @GET("facts.json")
    suspend fun getFacts(): Response<Fact>

}