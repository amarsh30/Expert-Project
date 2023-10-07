package com.amar.expertproject.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amar.expertproject.core.domain.model.Restaurant
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {

    fun getRestaurantDetail(idRestaurant: String) =
        restaurantUseCase.getDetailRestaurant(idRestaurant).asLiveData()
    fun setFavoriteRestaurant(restaurant: Restaurant, newStatus:Boolean) =
        restaurantUseCase.setFavoriteRestaurant(restaurant, newStatus)
}
