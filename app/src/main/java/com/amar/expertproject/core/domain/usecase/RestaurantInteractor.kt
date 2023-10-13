package com.amar.expertproject.core.domain.usecase

import com.amar.expertproject.core.data.Resource
import com.amar.expertproject.core.domain.model.Restaurant
import com.amar.expertproject.core.domain.repository.IRestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantInteractor @Inject constructor(private val restaurantRepository: IRestaurantRepository): RestaurantUseCase {

    override fun getAllRestaurant() = restaurantRepository.getAllRestaurant()

    override fun getFavoriteRestaurant() = restaurantRepository.getFavoriteRestaurant()
    override fun getDetailRestaurant(idRestaurant: String): Flow<Resource<Restaurant>> =
        restaurantRepository.getDetailRestaurant(idRestaurant)

    override fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean) = restaurantRepository.setFavoriteRestaurant(restaurant, isFavorite)
}