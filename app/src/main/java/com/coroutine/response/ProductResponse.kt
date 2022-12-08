package com.coroutine.response

import com.coroutine.model.ProductModel
import com.google.gson.annotations.SerializedName

data class ProductResponse(@SerializedName("products")
                           var productModel: ArrayList<ProductModel>)
