package com.coroutine.response

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.coroutine.model.ProductModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BannerConverter {


    @TypeConverter
    fun fromGsonToImageBannerConverter(value: String): List<String> {
//        val listType = object : TypeToken<List<ProductModel>>(){}.type // listType
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun fromImageBannerToGsonConverter(imageBanner: List<String?>): String {
        return Gson().toJson(imageBanner)
    }

    // Gson == String


}