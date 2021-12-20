package com.sgd.project.dollargraph.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sgd.project.dollargraph.repository.DollarRepository
import kotlinx.coroutines.CoroutineDispatcher

class DollarGraphViewModelFactory(private val dollarRepository: DollarRepository,
                                  private val dispatcher: CoroutineDispatcher
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DollarGraphViewModel(dollarRepository, dispatcher) as T
    }
}