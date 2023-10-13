package com.amar.expertproject.di

import com.amar.expertproject.core.domain.usecase.RestaurantUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteDependencies {
    fun restaurantUseCase(): RestaurantUseCase
}