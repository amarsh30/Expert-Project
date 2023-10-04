package com.amar.expertproject.core.data.source.remote.network

import com.amar.expertproject.core.data.source.remote.response.DetailRestaurantResponse
import com.amar.expertproject.core.data.source.remote.response.ListRestaurantResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getAllList(): ListRestaurantResponse

    @GET("detail")
    suspend fun getDetail(): DetailRestaurantResponse

}