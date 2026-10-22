package com.example.systemdesign.design.ecommerceProduct.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.design.ecommerceProduct.data.local.CartEntity
import com.example.systemdesign.design.ecommerceProduct.domain.repository.EComRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: EComRepository): ViewModel() {

     fun getCartList(): Flow<List<CartEntity>> {
        return  repository.getCartList()
    }

    fun removeProductFromCart(id: Int){
        viewModelScope.launch {
            repository.removeCartItem(id)
        }

    }



}