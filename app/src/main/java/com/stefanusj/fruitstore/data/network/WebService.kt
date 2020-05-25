package com.stefanusj.fruitstore.data.network

import com.stefanusj.fruitstore.model.response.FruitResponse
import retrofit2.http.GET

interface WebService {

    @GET("fruits.json")
    suspend fun getFruits(): FruitResponse

}