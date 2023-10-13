package com.amar.expertproject.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class FavoriteViewModel @Inject constructor(restaurantUseCase: RestaurantUseCase) : ViewModel() {
    val favoriteRestaurant = restaurantUseCase.getFavoriteRestaurant().asLiveData()
}
