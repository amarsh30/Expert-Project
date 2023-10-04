package com.amar.expertproject.core.data

import com.amar.expertproject.core.data.source.local.LocalDataSource
import com.amar.expertproject.core.data.source.remote.RemoteDataSource
import com.amar.expertproject.core.data.source.remote.network.ApiResponse
import com.amar.expertproject.core.data.source.remote.response.RestaurantResponse
import com.amar.expertproject.core.domain.model.Restaurant
import com.amar.expertproject.core.domain.repository.IRestaurantRepository
import com.amar.expertproject.core.utils.AppExecutors
import com.amar.expertproject.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestaurantRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRestaurantRepository {

    companion object {
        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): RestaurantRepository =
            instance ?: synchronized(this) {
                instance ?: RestaurantRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllRestaurant(): Flow<Resource<List<Restaurant>>> =
        object : NetworkBoundResource<List<Restaurant>, List<RestaurantResponse>>() {
            override fun loadFromDB(): Flow<List<Restaurant>> {
                return localDataSource.getAllRestaurant().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Restaurant>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<RestaurantResponse>>> =
                remoteDataSource.getAllRestaurant()

            override suspend fun saveCallResult(data: List<RestaurantResponse>) {
                val restaurantList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertRestaurant(restaurantList)
            }
        }.asFlow()

    override fun getFavoriteRestaurant(): Flow<List<Restaurant>> {
        return localDataSource.getFavoriteRestaurant().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteRestaurant(restaurant: Restaurant, state: Boolean) {
        val restaurantEntity = DataMapper.mapDomainToEntity(restaurant)
        appExecutors.diskIO().execute { localDataSource.setFavoriteRestaurant(restaurantEntity, state) }
    }
}