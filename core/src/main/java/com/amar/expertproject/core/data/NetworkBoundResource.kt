package com.amar.expertproject.core.data

import com.amar.expertproject.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<com.amar.expertproject.core.data.Resource<ResultType>> = flow {
        emit(com.amar.expertproject.core.data.Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(com.amar.expertproject.core.data.Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { com.amar.expertproject.core.data.Resource.Success(it) })
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { com.amar.expertproject.core.data.Resource.Success(it) })
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(com.amar.expertproject.core.data.Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { com.amar.expertproject.core.data.Resource.Success(it) })
        }
    }


    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<com.amar.expertproject.core.data.Resource<ResultType>> = result
}