package com.amar.expertproject.detail

import androidx.lifecycle.ViewModel
import com.amar.expertproject.core.domain.model.Restaurant
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase

class DetailRestaurantViewModel(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {
    fun setFavoriteRestaurant(restaurant: Restaurant, newStatus:Boolean) =
        restaurantUseCase.setFavoriteRestaurant(restaurant, newStatus)
}
