package com.coroutine.network.local

import android.content.Context
import androidx.room.*
import com.coroutine.common.Constant
import com.coroutine.response.BannerConverter
import com.coroutine.model.ProductModel
import dagger.Provides


@Database(entities = [ProductModel::class], version = 1)

@TypeConverters(BannerConverter::class)

abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {

        @Volatile
        private var instance: ProductDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ProductDatabase::class.java,
                Constant.PRODUCT_TABLE
            ).fallbackToDestructiveMigration().build()
    }


}