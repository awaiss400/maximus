package com.stone.maximustech

import com.stone.maximustech.model.Facts
import com.stone.maximustech.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class MyRepository @Inject constructor( private val apiService: ApiService) {

    suspend fun getfacts(): Flow<List<Facts>> = flow {
        emit(apiService.getFacts())
    }.flowOn(Dispatchers.IO)
    }