package com.sgd.project.dollargraph

import android.app.Application
import com.sgd.project.dollargraph.api.RateService
import com.sgd.project.dollargraph.api.RetrofitHelper
import com.sgd.project.dollargraph.repository.DollarRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DollarApplication: Application() {
}