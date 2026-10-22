package com.example.systemdesign.design.imageLoadingLibrary.data.cache

import android.graphics.Bitmap
import androidx.collection.LruCache

class MemoryCache {

    private val memoryCache = object : LruCache<String, Bitmap>(10*1024*1024){
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount
        }

    }

    fun  get(cacheKey:String): Bitmap?{
        return memoryCache.get(cacheKey);
    }

    fun put(cacheKey: String,bitmap: Bitmap){
        memoryCache.put(cacheKey,bitmap);
    }

    fun clearCache(){
        memoryCache.evictAll()
    }
}