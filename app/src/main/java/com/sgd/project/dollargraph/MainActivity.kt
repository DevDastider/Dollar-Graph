package com.sgd.project.dollargraph

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sgd.project.dollargraph.databinding.ActivityMainBinding
import com.sgd.project.dollargraph.viewmodels.DollarGraphViewModel
import com.sgd.project.dollargraph.viewmodels.DollarGraphViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dollarGraphViewModel: DollarGraphViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing ViewModel
        /*val repository = (application as DollarApplication).dollarRepository
        dollarGraphViewModel = ViewModelProvider(this,
            DollarGraphViewModelFactory(repository, Dispatchers.IO))
            .get(DollarGraphViewModel::class.java)*/

    /*if(savedInstanceState == null)
            supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, HomeFragment())
            .commit()*/

    }
}