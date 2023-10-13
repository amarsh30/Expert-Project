package com.amar.expertproject.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val restaurantUseCase: RestaurantUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != FavoriteViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return FavoriteViewModel(restaurantUseCase) as T
    }
}