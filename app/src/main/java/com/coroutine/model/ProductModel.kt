package com.coroutine.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "product")
data class ProductModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int ?= null,
    @SerializedName("title")
    val title: String ?= null,
    @SerializedName("price")
    val price: Double ?= null,
    @SerializedName("discountPercentage")
    val discountPercentage: Double ?= null,
    @SerializedName("description")
    val description: String ?= null,
    @SerializedName("thumbnail")
    val image: String ?= null,
    @SerializedName("rating")
    val rating: Float ?= null,
    @SerializedName("images")
    val images: List<String> ?= null

) : Serializable