package com.amar.expertproject.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amar.expertproject.core.di.Injection
import com.amar.expertproject.core.domain.usecase.RestaurantUseCase
import com.amar.expertproject.detail.DetailRestaurantViewModel
import com.amar.expertproject.favorite.FavoriteViewModel
import com.amar.expertproject.home.HomeViewModel

class ViewModelFactory private constructor(private val restaurantUseCase: RestaurantUseCase) :
    ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRestaurantUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(restaurantUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(restaurantUseCase) as T
            }
            modelClass.isAssignableFrom(DetailRestaurantViewModel::class.java) -> {
                DetailRestaurantViewModel(restaurantUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}