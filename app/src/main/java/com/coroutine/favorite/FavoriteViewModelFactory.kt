package com.coroutine.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coroutine.repository.ProductRepository
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(productRepository) as T
    }
}