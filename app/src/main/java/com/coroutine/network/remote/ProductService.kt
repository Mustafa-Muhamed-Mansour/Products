package com.coroutine.network.remote

import com.coroutine.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService
{

    @GET("products")
    suspend fun getProducts(): Response<ProductResponse>


}