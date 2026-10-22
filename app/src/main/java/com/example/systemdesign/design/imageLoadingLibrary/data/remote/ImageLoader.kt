package com.example.systemdesign.design.imageLoadingLibrary.data.remote

import android.graphics.Bitmap
import javax.inject.Inject

class ImageLoader @Inject constructor(val requestManager: RequestManager){
    suspend fun load(url: String,requiredWidth: Int,requiredHeight: Int): Bitmap{
      return  requestManager.loadImage(url,requiredWidth,requiredHeight);
    }
}