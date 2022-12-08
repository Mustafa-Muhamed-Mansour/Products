package com.coroutine.di

import android.app.Application
import com.coroutine.favorite.FavoriteViewModelFactory
import com.coroutine.network.local.ProductDatabase
import com.coroutine.product.ProductViewModelFactory
import com.coroutine.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Provides
    @Singleton
    fun providesProductRepository(dp: ProductDatabase): ProductRepository {
        return ProductRepository(dp)
    }


    @Provides
    @Singleton
    fun providesProductFactory(
        app: Application,
        productRepository: ProductRepository
    ): ProductViewModelFactory {
        return ProductViewModelFactory(app, productRepository)
    }

    @Provides
    @Singleton
    fun providesFavoriteFactory(productRepository: ProductRepository): FavoriteViewModelFactory {
        return FavoriteViewModelFactory(productRepository)
    }

}