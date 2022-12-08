package com.coroutine.common

sealed class ProductState<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T): ProductState<T>(data)
    class Error<T>(message: String, data: T? = null): ProductState<T>(data, message)
    class Loading<T>(): ProductState<T>()
}