package com.sgd.project.dollargraph.di

import com.sgd.project.dollargraph.api.RateService
import com.sgd.project.dollargraph.api.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRateService(): RateService{
        return RetrofitHelper.getInstance().create(RateService::class.java)
    }

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}