package com.example.systemdesign.design.imageLoadingLibrary.data.remote

import android.graphics.Bitmap
import com.example.systemdesign.design.imageLoadingLibrary.domain.ImageDownloadRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

 class RequestManager @Inject constructor(
     private val repository: ImageDownloadRepository
 ) {

     private val mutex = Mutex()
     private val scope =
         CoroutineScope(SupervisorJob() + Dispatchers.IO)

     private data class InFlightRequest(
         val deferred: Deferred<Bitmap>,
         var callers: Int = 0
     )

     private val inFlightRequest =
         mutableMapOf<String, InFlightRequest>()


     suspend fun loadImage(
         url: String,
         requiredWidth: Int,
         requiredHeight: Int
     ): Bitmap {

       /**Find existing request OR create new request*/

        val request = mutex.withLock {

             val existingRequest = inFlightRequest[url]

             if (existingRequest != null) {

                /**  Same URL is already downloading*/
                 existingRequest.callers++

                 existingRequest

             } else {

                /** First caller for this URL*/
                 val deferred = scope.async {

                     repository.loadImageFromUrl(
                         url,
                         requiredWidth,
                         requiredHeight
                     )
                 }

                 val newRequest = InFlightRequest(
                     deferred = deferred,
                     callers = 1
                 )

                 inFlightRequest[url] = newRequest

                 newRequest
             }
         }


        /** Wait outside the Mutex*/

         try {

             return request.deferred.await()

         } finally {

           /**  Caller completed OR caller was cancelled*/
             releaseCaller(url, request)
         }
     }


    /**Remove one caller*/
     private suspend fun releaseCaller(
         url: String,
         request: InFlightRequest
     ) {

         mutex.withLock {

             request.callers--

           /**   Nobody needs this request anymore*/
             if (request.callers == 0) {

                 if (inFlightRequest[url] === request) {

                     inFlightRequest.remove(url)

                     /** Cancel actual download*/
                     if (!request.deferred.isCompleted) {
                         request.deferred.cancel()
                     }
                 }
             }
         }
     }
 }