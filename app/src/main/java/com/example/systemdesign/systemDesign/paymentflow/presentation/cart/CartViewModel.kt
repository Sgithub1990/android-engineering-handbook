package com.example.systemdesign.systemDesign.paymentflow.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.systemDesign.paymentflow.data.local.cart.CartItemEntity
import com.example.systemdesign.systemDesign.paymentflow.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(val cartRepository: CartRepository): ViewModel() {

    val cartList = cartRepository.getCart().
      stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
      )


    fun increaseQuantity(productId: Long){
        viewModelScope.launch {
            cartRepository.increaseCart(productId)
        }
    }

    fun decreaseQuantity(productId: Long){
        viewModelScope.launch {
            cartRepository.decreaseCart(productId)
        }
    }

    fun deleteCart(productId: Long){
        viewModelScope.launch {
            cartRepository.deleteCart(productId)
        }
    }

    fun deleteCart(cartEntity: CartItemEntity){
        viewModelScope.launch {
            cartRepository.insertCart(cartEntity)
        }
    }






}