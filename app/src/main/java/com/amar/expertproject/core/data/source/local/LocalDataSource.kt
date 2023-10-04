package com.amar.expertproject.core.data.source.local

import com.amar.expertproject.core.data.source.local.entity.RestaurantEntity
import com.amar.expertproject.core.data.source.local.room.RestaurantDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val restaurantDao: RestaurantDao){
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(restaurantDao: RestaurantDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(restaurantDao)
            }
    }

    fun getAllRestaurant(): Flow<List<RestaurantEntity>> = restaurantDao.getAllRestaurant()

    fun getFavoriteRestaurant(): Flow<List<RestaurantEntity>> = restaurantDao.getFavoriteRestaurant()

    suspend fun insertRestaurant(restaurantList: List<RestaurantEntity>) = restaurantDao.insertRestaurant(restaurantList)

    fun setFavoriteRestaurant(restaurant: RestaurantEntity, newState: Boolean) {
        restaurant.isFavorite = newState
        restaurantDao.updateFavoriteRestaurant(restaurant)
    }
}