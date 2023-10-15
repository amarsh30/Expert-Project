package com.amar.expertproject.core.utils

import com.amar.expertproject.core.data.source.local.entity.RestaurantEntity
import com.amar.expertproject.core.data.source.remote.response.RestaurantResponse
import com.amar.expertproject.core.domain.model.Restaurant
import javax.inject.Inject

open class DataMapper @Inject constructor() :
    Mapper<RestaurantEntity, Restaurant, RestaurantResponse> {
    override fun mapEntitiesToDomain(type: RestaurantEntity): Restaurant {
        return Restaurant(
            restaurantId = type.restaurantId,
            name = type.name,
            description = type.description,
            pictureId = type.pictureId,
            city = type.city,
            rating = type.rating,
            isFavorite = type.isFavorite
        )
    }

    override fun mapDomainToEntity(type: Restaurant): RestaurantEntity {
        return RestaurantEntity(
            type.restaurantId,
            name = type.name,
            description = type.description,
            pictureId = type.pictureId,
            city = type.city,
            rating = type.rating,
            isFavorite = type.isFavorite
        )

    }

    override fun mapResponseToEntities(type: RestaurantResponse): RestaurantEntity {
        return RestaurantEntity(
            restaurantId = type.id,
            name = type.name,
            description = type.description,
            pictureId = type.pictureId,
            city = type.city,
            rating = type.rating,
            isFavorite = false
        )
    }

}