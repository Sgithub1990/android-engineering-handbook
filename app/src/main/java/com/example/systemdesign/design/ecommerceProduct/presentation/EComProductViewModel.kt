package com.example.systemdesign.design.ecommerceProduct.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.systemdesign.design.ecommerceProduct.data.local.CartEntity
import com.example.systemdesign.design.ecommerceProduct.data.local.EComProductEntity
import com.example.systemdesign.design.ecommerceProduct.domain.repository.EComRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EComProductViewModel @Inject constructor(val repository: EComRepository): ViewModel() {

    val productList = repository.getPager().cachedIn(viewModelScope)


    fun  insertProductIntoCart(product: EComProductEntity?){
        viewModelScope.launch {
            repository.insertIntoCart(product)
        }

    }



}