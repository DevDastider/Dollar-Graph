package com.sgd.project.dollargraph.repository


import com.sgd.project.dollargraph.api.RateService
import com.sgd.project.dollargraph.api.RetrofitHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DollarRepositoryTest{

    private lateinit var repository: DollarRepository

    @Before
    fun setup(){
        val rateService= RetrofitHelper.getInstance().create(RateService::class.java)
        repository= DollarRepository(rateService)
    }

    @Test
    fun checkGetRates(){
        //mainCoroutineRule.runBlockingTest {
        runBlocking {
            lateinit var list: ArrayList<Float>

            launch {
              list= repository.getRates("2021-01-01", "2021-12-01")
            }.join()

            assertEquals(list.size, 12)
        }
    }

    @Test(expected = NullPointerException::class)
    fun when_wrong_dates_sent(){
        runBlocking {
            lateinit var list: ArrayList<Float>

            launch {
                list= repository.getRates("2020-01-01", "2020-10-01")
            }.join()

            assertEquals(list.size, 10)
        }
    }
}