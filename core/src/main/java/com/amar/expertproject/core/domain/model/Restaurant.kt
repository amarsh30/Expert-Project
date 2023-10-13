package com.amar.expertproject.core.domain.model


data class Restaurant(
    val restaurantId: String,
    val name: String,
    val description: String,
    val pictureId: String,
    val city: String,
    val rating: Double,
    val isFavorite: Boolean
)