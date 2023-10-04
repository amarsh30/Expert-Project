package com.amar.expertproject.core.di

import android.content.Context
import com.amar.expertproject.core.data.RestaurantRepository
import com.amar.expertproject.core.data.source.local.LocalDataSource
import com.amar.expertproject.core.data.source.local.room.RestaurantDatabase
import com.amar.expertproject.core.data.source.remote.RemoteDataSource
import com.amar.expertproject.core.data.source.remote.network.ApiConfig
import com.amar.expertproject.core.domain.repository.IRestaurantRepository
import com.amar.expertproject.core.domain.usecase.RestaurantInteractor
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase
import com.amar.expertproject.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IRestaurantRepository {
        val database = RestaurantDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.restaurantDao())
        val appExecutors = AppExecutors()

        return RestaurantRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideRestaurantUseCase(context: Context): RestaurantUseCase {
        val repository = provideRepository(context)
        return RestaurantInteractor(repository)
    }
}