package com.sgd.project.dollargraph.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.sgd.project.dollargraph.databinding.FragmentBarGraphBinding
import com.sgd.project.dollargraph.viewmodels.DollarGraphViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BarGraphFragment : Fragment() {

    private lateinit var binding: FragmentBarGraphBinding
    private val dollarGraphViewModel: DollarGraphViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentBarGraphBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dollarGraphViewModel.barEntryList.observe(requireActivity(), Observer {
            configureChart(it)
        })
    }

    private fun configureChart(entryList: ArrayList<BarEntry>) {

        //create a line data set and attach it to bar chart
        val barDataSet= BarDataSet(entryList, "Dollar Chart")
        barDataSet.color= Color.RED

        val barData= BarData(barDataSet)

        //Configuring X-axis
        val xAxis= binding.barChart.xAxis
        xAxis.position= XAxis.XAxisPosition.BOTTOM          //To show x-axis in bottom
        xAxis.labelCount = 11                               //Setting label count
        xAxis.valueFormatter= object: ValueFormatter(){     //Formatting x-axis labels
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}/21"
            }
        }

        setChart(barData)
    }

    private fun setChart(barData: BarData) {
        binding.barChart.data= barData
        binding.barChart.invalidate()
    }
}