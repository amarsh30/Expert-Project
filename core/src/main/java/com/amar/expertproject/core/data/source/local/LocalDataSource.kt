package com.amar.expertproject.core.data.source.local

import com.amar.expertproject.core.data.source.local.entity.RestaurantEntity
import com.amar.expertproject.core.data.source.local.room.RestaurantDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val restaurantDao: RestaurantDao){

    fun getAllRestaurant(): Flow<List<RestaurantEntity>> = restaurantDao.getAllRestaurant()

    fun getDetailRestaurant(idRestaurant: String): Flow<RestaurantEntity> = restaurantDao.getDetailRestaurant(idRestaurant)

    fun getFavoriteRestaurant(): Flow<List<RestaurantEntity>> = restaurantDao.getFavoriteRestaurant()

    suspend fun insertRestaurant(restaurantList: List<RestaurantEntity>) = restaurantDao.insertRestaurant(restaurantList)

    fun setFavoriteRestaurant(restaurant: RestaurantEntity, isFavorite: Boolean) {
        restaurant.isFavorite = isFavorite
        restaurantDao.updateFavoriteRestaurant(restaurant)
    }
}