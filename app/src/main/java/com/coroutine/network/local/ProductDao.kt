package com.coroutine.network.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.coroutine.model.ProductModel
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(productModel: ProductModel): Long


    @Query("select * from product")
    fun getAllProduct(): LiveData<List<ProductModel>>


    @Delete
    suspend fun deleteProduct(productModel: ProductModel)
}