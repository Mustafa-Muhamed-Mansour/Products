package com.coroutine.interfaces

import com.coroutine.model.ProductModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface ProductDetail
{
    fun clickedOfProduct(productModel: ProductModel)
}