package com.amar.expertproject.core.data.source.remote

import android.util.Log
import com.amar.expertproject.core.data.source.remote.network.ApiResponse
import com.amar.expertproject.core.data.source.remote.network.ApiService
import com.amar.expertproject.core.data.source.remote.response.RestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService){
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllRestaurant(): Flow<ApiResponse<List<RestaurantResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getAllList()
                val dataArray = response.restaurants
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.restaurants))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}