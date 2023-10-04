package com.amar.expertproject.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase

class HomeViewModel(restaurantUseCase: RestaurantUseCase) : ViewModel() {
    val restaurant = restaurantUseCase.getAllRestaurant().asLiveData()
}