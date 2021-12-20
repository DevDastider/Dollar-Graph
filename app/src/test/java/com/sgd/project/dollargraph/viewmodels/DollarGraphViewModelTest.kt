package com.sgd.project.dollargraph.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.BarEntry
import com.google.common.truth.Truth.assertThat
import com.sgd.project.dollargraph.CoroutineTestRule
import com.sgd.project.dollargraph.api.RateService
import com.sgd.project.dollargraph.api.RetrofitHelper
import com.sgd.project.dollargraph.repository.DollarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DollarGraphViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule= CoroutineTestRule()

    @get:Rule
    var testRule= InstantTaskExecutorRule()

    private lateinit var repository: DollarRepository
    private lateinit var viewModel: DollarGraphViewModel

    //private val mockRepository= Mockito.mock(DollarRepository::class.java)

    @Before
    fun setup(){
        val rateService= RetrofitHelper.getInstance().create(RateService::class.java)
        repository = DollarRepository(rateService)
    }

    @Test
    fun checkBarEntry(){
        val observer= Observer<ArrayList<BarEntry>>{ }

        runBlocking {
            try {
                launch {
                    viewModel = DollarGraphViewModel(repository, Dispatchers.Main)

                    repository.getRates("2021-01-01", "2021-12-01")

                    viewModel.barEntryList.observeForever(observer)
                }.join()

                val value = viewModel.barEntryList.value
                println(value)
                assertThat(value?.size).isEqualTo(null)
            }
            finally {
                viewModel.barEntryList.removeObserver(observer)
            }
        }
    }
}