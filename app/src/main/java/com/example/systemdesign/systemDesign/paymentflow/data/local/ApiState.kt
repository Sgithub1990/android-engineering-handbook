package com.example.systemdesign.systemDesign.paymentflow.data.local

sealed class ApiState<out T> {

      data class Success<T>(val result:T): ApiState<T>()

      data object Loading: ApiState<Nothing>()

      data class Error(val error: String): ApiState<Nothing>()

}