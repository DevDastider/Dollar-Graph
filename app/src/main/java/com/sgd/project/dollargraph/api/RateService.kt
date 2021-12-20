package com.sgd.project.dollargraph.api

import com.sgd.project.dollargraph.models.RateList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RateService {

    //Fetching dollar rates
    @GET("/timeseries?base=USD&symbols=INR")
    suspend fun getRate(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Response<RateList>
}