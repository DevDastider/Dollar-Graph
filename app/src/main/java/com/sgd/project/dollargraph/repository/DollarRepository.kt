package com.sgd.project.dollargraph.repository

import com.sgd.project.dollargraph.api.RateService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DollarRepository @Inject constructor(private val rateService: RateService) {

    suspend fun getRates(startDate: String, endDate: String): ArrayList<Float> {
        val rate= rateService.getRate(startDate, endDate).body()
        val rateList= ArrayList<Float>()

        if (rate != null){
            if (rateList.isNotEmpty()){
                rateList.clear()
            }
            rateList.add(rate.rates.`2021-01-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-02-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-03-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-04-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-05-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-06-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-07-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-08-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-09-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-10-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-11-01`.INR.toFloat())
            rateList.add(rate.rates.`2021-12-01`.INR.toFloat())
        }
        //Log.d("RateList",rateList.toString())

        return rateList
    }
}