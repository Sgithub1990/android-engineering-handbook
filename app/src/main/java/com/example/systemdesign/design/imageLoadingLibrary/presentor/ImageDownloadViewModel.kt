package com.example.systemdesign.design.imageLoadingLibrary.presentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.design.imageLoadingLibrary.data.remote.ImageLoader
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageDownloadViewModel @Inject constructor(val imageLoader: ImageLoader): ViewModel() {

    fun  loadImage(url: String){
       viewModelScope.launch {
           imageLoader.load(url,200,200)

       }
   }

}