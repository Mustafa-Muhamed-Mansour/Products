package com.coroutine.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutine.model.ProductModel
import com.coroutine.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {


    fun insertProduct(productModel: ProductModel) {
        viewModelScope.launch {
            productRepository.insertProduct(productModel)
        }
    }

    fun getAllProduct() : LiveData<List<ProductModel>> {
        return productRepository.getSavedProduct()
    }

    fun deleteProduct(productModel: ProductModel) {
        viewModelScope.launch {
            productRepository.deleteProduct(productModel)
        }
    }

}