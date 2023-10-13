package com.amar.expertproject.core.domain.usecase

import com.amar.expertproject.core.data.Resource
import com.amar.expertproject.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCase {
    fun getAllRestaurant(): Flow<Resource<List<Restaurant>>>
    fun getFavoriteRestaurant(): Flow<List<Restaurant>>
    fun getDetailRestaurant(idRestaurant: String): Flow<Resource<Restaurant>>
    fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean)
}