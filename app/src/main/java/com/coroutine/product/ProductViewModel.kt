package com.coroutine.product

import android.app.Application
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutine.model.ProductModel
import com.coroutine.repository.ProductRepository
import com.coroutine.response.ProductResponse
import com.coroutine.common.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val app: Application,
    private val productRepository: ProductRepository,
) : ViewModel() {

    val productMutableLiveData: MutableLiveData<ProductState<ProductResponse>> = MutableLiveData()


    init {
        getProduct()
    }

    private fun getProduct() {
        viewModelScope.launch {
//            productMutableLiveData.emit(Resource.Loading())
            productMutableLiveData.postValue(ProductState.Loading())
            val response = productRepository.getProduct()
//            productMutableLiveData.emit(handleProductResponse(response))
            productMutableLiveData.postValue(handleProductResponse(response))
        }
    }

    private fun handleProductResponse(response: Response<ProductResponse>): ProductState<ProductResponse> {
        if (response.isSuccessful) {
            try {
                response.body()?.let {
                    return ProductState.Success(it)
                }

            } catch (e: Exception) {
                return ProductState.Error(e.message.toString())
            }
        }
        return ProductState.Error(response.message())
    }


    fun insertProduct(productModel: ProductModel) {
        viewModelScope.launch {
            productRepository.insertProduct(productModel)
        }
    }

}