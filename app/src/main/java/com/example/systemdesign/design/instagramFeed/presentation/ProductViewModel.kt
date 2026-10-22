package com.example.systemdesign.design.instagramFeed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.systemdesign.design.instagramFeed.domain.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val repository: ProductRepository): ViewModel() {


    val products = repository.getPagingData().cachedIn(viewModelScope)


    fun syncLike(productId: Int){
        repository.syncLike(productId)
    }
}