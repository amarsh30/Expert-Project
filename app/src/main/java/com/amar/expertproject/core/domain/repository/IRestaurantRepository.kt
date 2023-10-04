package com.amar.expertproject.core.domain.repository

import com.amar.expertproject.core.data.Resource
import com.amar.expertproject.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface IRestaurantRepository {

    fun getAllRestaurant(): Flow<Resource<List<Restaurant>>>

    fun getFavoriteRestaurant(): Flow<List<Restaurant>>

    fun setFavoriteRestaurant(restaurant: Restaurant, state: Boolean)
}