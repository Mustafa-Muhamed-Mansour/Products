package com.coroutine.repository

import androidx.lifecycle.LiveData
import com.coroutine.common.ProductState
import com.coroutine.model.ProductModel
import com.coroutine.network.local.ProductDatabase
import com.coroutine.di.RetrofitModule
import com.coroutine.response.ProductResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val db: ProductDatabase
) {

    suspend fun getProduct(): Response<ProductResponse> {

        val product = RetrofitModule()
        return product.getApiProduct().getProducts()
    }


    suspend fun insertProduct(productModel: ProductModel) {

        db.getProductDao().insertProduct(productModel)
    }

    fun getSavedProduct(): LiveData<List<ProductModel>> {

        return db.getProductDao().getAllProduct()
    }

    suspend fun deleteProduct(productModel: ProductModel) {

        db.getProductDao().deleteProduct(productModel)
    }
}