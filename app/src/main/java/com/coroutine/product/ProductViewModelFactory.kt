package com.coroutine.product

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coroutine.repository.ProductRepository
import javax.inject.Inject

class ProductViewModelFactory @Inject constructor(
    private val app: Application,
    private val productRepository: ProductRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(app, productRepository) as T
    }
}