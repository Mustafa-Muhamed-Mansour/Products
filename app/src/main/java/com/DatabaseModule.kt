package com


class DatabaseModule {

//    private lateinit var retrofit: Retrofit
//    val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
//    private lateinit var client: OkHttpClient


//    @Provides
//    fun providesBaseUrl(): String {
//        return Constant.BASE_URL
//    }

//    @Provides
//    @Singleton
//    fun providesRetrofit(): Retrofit {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .callTimeout(60, TimeUnit.SECONDS)
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(logging)
//            .build()
//
//        retrofit = Retrofit.Builder()
//            .baseUrl(providesBaseUrl())
//            .addConverterFactory(providesGsonConverterFactory())
////            .addConverterFactory(ScalarsConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(client)
//            .build()
//
//        return retrofit
//    }


//    @Provides
//    @Singleton
//    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }


//    @Provides
//    @Singleton
//    fun providesOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .callTimeout(60, TimeUnit.SECONDS)
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//        return okHttpClient.build()
//    }


//    @Provides
//    @Singleton
//    fun providesRetrofit(
//        baseUrl: String,
////        converterFactory: Converter.Factory,
//        client: OkHttpClient
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//    }

//    @Provides
//    @Singleton
//    fun providesGsonConverterFactory(): Converter.Factory {
//        return GsonConverterFactory.create()
//    }

//    @Provides
//    @Singleton
//    fun providesProductDatabase(context: Context) = Room.databaseBuilder(
//        context.applicationContext,
//        ProductDatabase::class.java,
//        Constant.PRODUCT_TABLE
//    ).fallbackToDestructiveMigration().build()


//    @Provides
//    @Singleton
//    fun providesRetrofitService(retrofit: Retrofit): ProductService {
//        return retrofit.create(ProductService::class.java)
//    }

//    @Provides
//    fun provideRetrofit():Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Constant.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//
//    @Provides
//    fun getApiProduct(retrofit: Retrofit):ProductService {
//        return retrofit.create(ProductService::class.java)
//    }


//    @Provides
//    @Singleton
//    fun providesProductRepository(dp: ProductDatabase): ProductRepository {
//        return ProductRepository(dp)
//    }
//
//
//    @Provides
//    @Singleton
//    fun providesProductFactory(
//        app: Application,
//        productRepository: ProductRepository
//    ): ProductViewModelFactory {
//        return ProductViewModelFactory(app, productRepository)
//    }
//
//    @Provides
//    @Singleton
//    fun providesFavoriteFactory(productRepository: ProductRepository): FavoriteViewModelFactory {
//        return FavoriteViewModelFactory(productRepository)
//    }
}