package com.example.systemdesign.systemDesign.paymentflow.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.systemDesign.paymentflow.data.local.ApiState
import com.example.systemdesign.systemDesign.paymentflow.data.local.cart.CartDao
import com.example.systemdesign.systemDesign.paymentflow.data.local.cart.CartItemEntity
import com.example.systemdesign.systemDesign.paymentflow.data.local.order.ProductOrderEntity
import com.example.systemdesign.systemDesign.paymentflow.data.local.product.ProductEntity
import com.example.systemdesign.systemDesign.paymentflow.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val repository: ProductRepository,val cartDao: CartDao): ViewModel(){

    val products = repository.getProducts().stateIn(scope=viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ApiState.Loading
    )




     fun addToCart(product: ProductEntity){
         viewModelScope.launch {
            val cart = CartItemEntity(product.id,product.title,product.price,product.thumbnail,product.rating,product.stock,1)
             cartDao.insertCart(cart)
         }

    }






    }

