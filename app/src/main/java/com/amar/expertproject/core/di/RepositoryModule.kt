package com.amar.expertproject.core.di

import com.amar.expertproject.core.data.RestaurantRepository
import com.amar.expertproject.core.domain.repository.IRestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(restaurantRepository: RestaurantRepository): IRestaurantRepository
}