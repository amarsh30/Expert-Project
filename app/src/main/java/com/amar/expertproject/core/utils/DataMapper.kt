package com.amar.expertproject.core.utils

import com.amar.expertproject.core.data.source.local.entity.RestaurantEntity
import com.amar.expertproject.core.data.source.remote.response.RestaurantResponse
import com.amar.expertproject.core.domain.model.Restaurant

object DataMapper {
    fun mapResponsesToEntities(input: List<RestaurantResponse>): List<RestaurantEntity> {
        val restaurantList = ArrayList<RestaurantEntity>()
        input.map {
            val restaurant = RestaurantEntity(
                restaurantId = it.id,
                name = it.name,
                description = it.description,
                pictureId = it.pictureId,
                city = it.city,
                rating = it.rating,
                isFavorite = false
            )
            restaurantList.add(restaurant)
        }
        return restaurantList
    }

    fun mapEntitiesToDomain(input: List<RestaurantEntity>): List<Restaurant> =
        input.map {
            Restaurant(
                restaurantId = it.restaurantId,
                name = it.name,
                description = it.description,
                pictureId = it.pictureId,
                city = it.city,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Restaurant) = RestaurantEntity(
        restaurantId = input.restaurantId,
        name = input.name,
        description = input.description,
        pictureId = input.pictureId,
        city = input.city,
        rating = input.rating,
        isFavorite = input.isFavorite
    )
}