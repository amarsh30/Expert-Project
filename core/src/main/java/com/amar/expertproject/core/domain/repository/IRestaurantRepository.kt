package com.amar.expertproject.core.domain.repository

import com.amar.expertproject.core.data.Resource
import com.amar.expertproject.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface IRestaurantRepository {

    fun getAllRestaurant(): Flow<com.amar.expertproject.core.data.Resource<List<Restaurant>>>
    fun getDetailRestaurant(idRestaurant: String): Flow<com.amar.expertproject.core.data.Resource<Restaurant>>

    fun getFavoriteRestaurant(): Flow<List<Restaurant>>

    fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean)
}