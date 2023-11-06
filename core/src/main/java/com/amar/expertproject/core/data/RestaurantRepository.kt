package com.amar.expertproject.core.data

import com.amar.expertproject.core.data.source.local.LocalDataSource
import com.amar.expertproject.core.data.source.remote.RemoteDataSource
import com.amar.expertproject.core.data.source.remote.network.ApiResponse
import com.amar.expertproject.core.data.source.remote.response.DetailRestaurantResponse
import com.amar.expertproject.core.data.source.remote.response.RestaurantResponse
import com.amar.expertproject.core.domain.model.Restaurant
import com.amar.expertproject.core.domain.repository.IRestaurantRepository
import com.amar.expertproject.core.utils.AppExecutors
import com.amar.expertproject.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val dataMapper: DataMapper
) : IRestaurantRepository {

    override fun getAllRestaurant(): Flow<Resource<List<Restaurant>>> =
        object :
            NetworkBoundResource<List<Restaurant>, List<RestaurantResponse>>() {
            override fun loadFromDB(): Flow<List<Restaurant>> {
                return localDataSource.getAllRestaurant().map { listRestaurant ->
                    listRestaurant.map {
                        dataMapper.mapEntitiesToDomain(it)
                    }

                }
            }

            override fun shouldFetch(data: List<Restaurant>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<RestaurantResponse>>> =
                remoteDataSource.getAllRestaurant()

            override suspend fun saveCallResult(data: List<RestaurantResponse>) {
                val restaurantList = data.map {
                    dataMapper.mapResponseToEntities(it)
                }
                localDataSource.insertRestaurant(restaurantList)
            }
        }.asFlow()


    override fun getDetailRestaurant(idRestaurant: String): Flow<Resource<Restaurant>> =
        object :
            NetworkBoundResource<Restaurant, DetailRestaurantResponse>() {
            override fun loadFromDB(): Flow<Restaurant> {
                return localDataSource.getDetailRestaurant(idRestaurant).map {
                    dataMapper.mapEntitiesToDomain(it)

                }
            }

            override fun shouldFetch(data: Restaurant?): Boolean {
                return data?.run {
                    name.isEmpty() || pictureId.isEmpty() || description.isEmpty() || city.isEmpty()
                } ?: false

            }

            override suspend fun createCall(): Flow<ApiResponse<DetailRestaurantResponse>> =
                remoteDataSource.getDetailRestaurant(idRestaurant)

            override suspend fun saveCallResult(data: DetailRestaurantResponse) {
                localDataSource.getDetailRestaurant(idRestaurant)
            }
        }.asFlow()


    override fun getFavoriteRestaurant(): Flow<List<Restaurant>> {
        return localDataSource.getFavoriteRestaurant().map { listFavorite ->
            listFavorite.map {
                dataMapper.mapEntitiesToDomain(it)
            }
        }
    }

    override fun setFavoriteRestaurant(restaurant: Restaurant, isFavorite: Boolean) {
        val restaurantEntity = dataMapper.mapDomainToEntity(restaurant)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteRestaurant(restaurantEntity, isFavorite) }
    }
}