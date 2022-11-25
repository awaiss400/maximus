package com.stone.maximustech.network

import com.stone.maximustech.model.Facts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

//interface ApiService{
//    companion object{
//        const val BASE_URL="https://catfact.ninja"
//    }
//@GET(value = "/fact")
//suspend fun getFacts():Response<Facts>
//}
interface ApiService{
    companion object{
        const val BASE_URL="https://catfact.ninja/"
    }
    @GET(value = "/fact")
    suspend fun getFacts(): Facts
}