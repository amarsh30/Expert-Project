package com.amar.expertproject.core.data.source.remote.network

import com.amar.expertproject.core.data.source.remote.response.DetailRestaurantResponse
import com.amar.expertproject.core.data.source.remote.response.ListRestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun getAllList(): ListRestaurantResponse

    @GET("detail/{id}")
    suspend fun getDetail(
        @Path("id") id: String
    ): DetailRestaurantResponse

}