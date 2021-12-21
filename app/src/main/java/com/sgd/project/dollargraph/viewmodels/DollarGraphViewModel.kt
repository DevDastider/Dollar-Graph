package com.sgd.project.dollargraph.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.sgd.project.dollargraph.di.IoDispatcher
import com.sgd.project.dollargraph.repository.DollarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DollarGraphViewModel @Inject constructor(private val dollarRepository: DollarRepository,
                           @IoDispatcher private val dispatcher:CoroutineDispatcher): ViewModel() {

    private val lineEntryListLiveData= MutableLiveData<ArrayList<Entry>>()
    val lineEntryList: LiveData<ArrayList<Entry>>
        get() = lineEntryListLiveData

    private val barEntryListLiveData= MutableLiveData<ArrayList<BarEntry>>()
    val barEntryList: LiveData<ArrayList<BarEntry>>
        get() = barEntryListLiveData

    init {
        viewModelScope.launch(dispatcher) {
            //To fetch data of last 1 year
            val startDate= "2021-01-01"
            val endDate= "2021-12-01"
            val rates= dollarRepository.getRates(startDate, endDate)
            generateLineEntryList(rates)
            generateBarEntryList(rates)
        }
    }

    private fun generateLineEntryList(rates: ArrayList<Float>) {
        val lineEntry= ArrayList<Entry>()
        for (i in 0 until rates.size){
            val x= (i+1).toFloat()
            val y= rates[i]
            lineEntry.add(Entry(x, y))
        }
        lineEntryListLiveData.postValue(lineEntry)
    }

    private fun generateBarEntryList(rates: ArrayList<Float>) {
        val barEntry= ArrayList<BarEntry>()
        for (i in 0 until rates.size){
            val x= (i+1).toFloat()
            val y= rates[i]
            barEntry.add(BarEntry(x, y))
        }
        barEntryListLiveData.postValue(barEntry)
    }
}