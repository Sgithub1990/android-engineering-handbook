package com.example.systemdesign.design.instagramFeed.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import javax.inject.Inject

class PaginViewModel @Inject constructor(val repository: Repository): ViewModel() {


        val result = repository.pager().cachedIn(viewModelScope)


}