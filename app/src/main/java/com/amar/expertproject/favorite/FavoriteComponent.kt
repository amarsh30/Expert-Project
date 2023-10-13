package com.amar.expertproject.favorite

import android.content.Context
import com.amar.expertproject.di.FavoriteDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteDependencies::class])
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dependencies(favoriteDependencies: FavoriteDependencies): Builder
        fun build(): FavoriteComponent
    }
}