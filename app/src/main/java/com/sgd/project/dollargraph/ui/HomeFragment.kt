package com.sgd.project.dollargraph.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sgd.project.dollargraph.R
import com.sgd.project.dollargraph.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.LineGraph.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_lineGraphFragment)
        }

        binding.BarGraph.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_barGraphFragment)
        }
    }
}