package com.amar.expertproject.core.domain.usecase

import com.amar.expertproject.core.domain.model.Restaurant
import com.amar.expertproject.core.domain.repository.IRestaurantRepository

class RestaurantInteractor(private val restaurantRepository: IRestaurantRepository): RestaurantUseCase {

    override fun getAllRestaurant() = restaurantRepository.getAllRestaurant()

    override fun getFavoriteRestaurant() = restaurantRepository.getFavoriteRestaurant()

    override fun setFavoriteRestaurant(restaurant: Restaurant, state: Boolean) = restaurantRepository.setFavoriteRestaurant(restaurant, state)
}