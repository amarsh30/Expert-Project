package com.amar.expertproject.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchRestaurantResponse(

    @field:SerializedName("pictureId")
    val pictureId: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("rating")
    val rating: Any,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: String

//    @field:SerializedName("founded")
//	val founded: Int,
//
//    @field:SerializedName("restaurants")
//	val restaurants: List<RestaurantsItem>,
//
//    @field:SerializedName("error")
//	val error: Boolean
)
