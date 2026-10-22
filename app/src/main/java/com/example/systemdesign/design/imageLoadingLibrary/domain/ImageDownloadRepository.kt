package com.example.systemdesign.design.imageLoadingLibrary.domain

import android.graphics.Bitmap

interface ImageDownloadRepository {

    suspend fun loadImageFromUrl(url: String,requiredWidth: Int,requiredHeight: Int): Bitmap
}