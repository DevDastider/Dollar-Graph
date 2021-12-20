package com.sgd.project.dollargraph.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.sgd.project.dollargraph.databinding.FragmentLineGraphBinding
import com.sgd.project.dollargraph.viewmodels.DollarGraphViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LineGraphFragment : Fragment() {

    private lateinit var binding: FragmentLineGraphBinding
    private val dollarGraphViewModel: DollarGraphViewModel by viewModels()//by activityViewModels()
    //private lateinit var lineData: LineData
    //private val entryList= ArrayList<Entry>()
    //private val rateList= ArrayList<Float>()

    /*companion object {
        fun newInstance(): LineGraphFragment {
            var instance: LineGraphFragment? = null
            return instance ?: LineGraphFragment().also {
                instance= it
            }
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLineGraphBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dollarGraphViewModel.lineEntryList.observe(requireActivity(), Observer {
            Log.d("Dollar", it.toString())
            configureChart(it)
        })
        /*val repository= (requireActivity().application as DollarApplication).dollarRepository
        dollarGraphViewModel= ViewModelProvider(requireActivity(),
            DollarGraphViewModelFactory(repository)).get(DollarGraphViewModel::class.java)*/

        /*dollarGraphViewModel.rates.observe(requireActivity(), Observer {
            //rateList.addAll(it)
            configureChart(it)
            setChart()
        })*/

        //Log.d("Dollar", rateList.toString())
        /*configureChart()
        setChart()*/
    }

    private fun configureChart(entryList: ArrayList<Entry>) {
        /*for (i in 0 until rateList.size){
            val x= (i+1).toFloat()
            val y= rateList[i]
            entryList.add(Entry(x, y))
        }*/

        //create a line data set and attach it to line chart
        val lineDataSet= LineDataSet(entryList, "Dollar Chart")

        lineDataSet.color= Color.BLUE
        lineDataSet.setCircleColor(Color.BLACK)
        lineDataSet.circleRadius= 4f

        lineDataSet.lineWidth= 3f

        lineDataSet.fillAlpha= 110
        val lineData= LineData(lineDataSet)

        //Configuring X-axis
        val xAxis= binding.lineChart.xAxis
        xAxis.position= XAxis.XAxisPosition.BOTTOM          //To show x-axis in bottom
        xAxis.labelCount = 11                               //Setting label count
        xAxis.valueFormatter= object: ValueFormatter(){     //Formatting x-axis labels
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}/21"
            }
        }

        setChart(lineData)
    }

    private fun setChart(lineData: LineData) {
        binding.lineChart.data= lineData    //Setting data in chart
        binding.lineChart.invalidate()      //It is called to refresh the data
    }

}