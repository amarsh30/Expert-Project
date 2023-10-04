package com.amar.expertproject.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase

class FavoriteViewModel(restaurantUseCase: RestaurantUseCase) : ViewModel() {
    val favoriteRestaurant = restaurantUseCase.getFavoriteRestaurant().asLiveData()
}
