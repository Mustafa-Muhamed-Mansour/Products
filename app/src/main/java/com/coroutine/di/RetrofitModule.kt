package com.coroutine.di

import com.coroutine.common.Constant
import com.coroutine.network.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
//    companion object
//    {
//        private val retrofit by lazy {
//            val log = HttpLoggingInterceptor()
//            log.setLevel(HttpLoggingInterceptor.Level.BODY)
//            val client = OkHttpClient.Builder()
//                .addInterceptor(log)
//                .build()
//
//            Retrofit.Builder()
//                .baseUrl(Constant.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//
//        val getApiProduct: ProductService by lazy {
//            retrofit.create(ProductService::class.java)
//        }


    @Provides
    @Singleton
    fun providesBaseUrl(): String {
        return Constant.BASE_URL
    }


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(providesBaseUrl())
//            .baseUrl(Constant.BASE_URL)
            .client(OkHttpClient()
                .newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    HttpLoggingInterceptor.Level.BODY
                }).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun getApiProduct(): ProductService {
        return provideRetrofit().create(ProductService::class.java)
    }
//    }
}