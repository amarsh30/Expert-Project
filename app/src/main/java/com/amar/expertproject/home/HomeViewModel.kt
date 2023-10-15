package com.amar.expertproject.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(restaurantUseCase: RestaurantUseCase) : ViewModel() {
    val restaurant = restaurantUseCase.getAllRestaurant().asLiveData()
}