package com.example.systemdesign.design.imageLoadingLibrary.data.repository

import android.graphics.Bitmap
import com.example.systemdesign.design.imageLoadingLibrary.data.cache.CacheGenerator
import com.example.systemdesign.design.imageLoadingLibrary.data.cache.DiskCache
import com.example.systemdesign.design.imageLoadingLibrary.data.cache.MemoryCache
import com.example.systemdesign.design.imageLoadingLibrary.data.downSampling.DownSampling
import com.example.systemdesign.design.imageLoadingLibrary.data.remote.ImageDownLoadClient
import com.example.systemdesign.design.imageLoadingLibrary.domain.ImageDownloadRepository
import javax.inject.Inject

class ImageDownLoadRepositoryImp @Inject constructor(
    val memoryCache: MemoryCache,
    val diskCache: DiskCache,
    val imageDownLoadClient:ImageDownLoadClient,
    val  keyGenerator: CacheGenerator,
    val downSampling: DownSampling

) : ImageDownloadRepository {
    override suspend fun loadImageFromUrl(url: String,requiredWidth: Int,requiredHeight: Int): Bitmap {

      /** get key */
      val key = keyGenerator.generate(url)

       /**check memory cache*/
        memoryCache.get(key)?.let {
            return it
        }

        /**check memory cache*/

          diskCache.get(key).let {
             if(it!=null) {
             /* val bitmap = decoder.decode(it)*/

                /**  Downsample while decoding*/
                 val bitmap = downSampling.decode(
                     file = it,
                     requiredWith = requiredWidth,
                     requiredHeight = requiredHeight
                 )


              memoryCache.put(key,bitmap)
               return bitmap
             }

          }

          /**download from network */
         val data =  imageDownLoadClient.downLoad(url)

        /**save to disc cache*/
       val file = diskCache.put(key,data!!)



       /* val bitmap = decoder.decode(data)*/

       /** Decode from File with downsampling*/
       val bitmap = downSampling.decode(
           file = file,
           requiredWith = requiredWidth,
           requiredHeight = requiredHeight
       )

        /*save to memory cache*/
        memoryCache.put(key,bitmap)

        return bitmap


     }



}