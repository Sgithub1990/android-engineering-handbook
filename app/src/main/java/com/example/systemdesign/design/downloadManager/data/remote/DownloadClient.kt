package com.example.systemdesign.design.downloadManager.data.remote

import com.example.systemdesign.design.downloadManager.data.local.DownloadDao
import com.example.systemdesign.design.downloadManager.data.local.DownloadEntity
import com.example.systemdesign.design.downloadManager.data.local.DownloadStatus
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class DownloadClient @Inject constructor(val dao: DownloadDao,val api: DownloadApi) {

    suspend fun downLoadFile(downloadEntity: DownloadEntity,outPutFile: File,progressCallback:suspend (progress: Int)->Unit){

        /** Check how many bytes already doe=wnload.*/
        val existingBytes = outPutFile.length()

        /** Create Range header.*/

        val range =
            if (existingBytes > 0) {
                "bytes=$existingBytes-"
            } else {
                null
            }


       /** Make HTTP request*/
       val response = api.downLoadFile(
           url = downloadEntity.url!!,
           range = range
       )


       /**initial update database*/
       dao.updateProgress(
           id = downloadEntity.id,
           bytes = 0L,
           total = 0L,
            path = outPutFile.absolutePath,
            name = outPutFile.name,
            status = DownloadStatus.DOWNLOADING
       )

       /**handle download request*/
       if(!response.isSuccessful){
           throw IOException("HTTP ${response.code()}")
       }

        /** check resume support*/
        if(existingBytes>0 && response.code()!=206){
            throw IOException("Server does not support resume")
        }

       val body = response.body()
        /** remaining bytes = body?.contentLength() */
        val remainingBytes = body?.contentLength()

       /**calculate total bytes*/

       val totalBytes = if((remainingBytes ?: 0) > 0){
           remainingBytes?.plus(existingBytes)
       }else{
           null
       }

       /**calculate downloaded  bytes*/
       var downLoadBytes = existingBytes

      /** update database*/

        dao.updateProgress(
            id = downloadEntity.id,
            bytes = downLoadBytes,
            total = totalBytes,
            path = outPutFile.absolutePath,
            name = outPutFile.name,
            status = DownloadStatus.DOWNLOADING
        )

       /**
        byteStream -> we can not download file at once so use byteStream
        .use -> automatically closes the stream when you're finished.
       */
       body?.byteStream()?.use {inputStream->
            /**this is where data will save */

           FileOutputStream(outPutFile,existingBytes>0)
           .use {outputStream->
           /**
           create buffer  = ByteArray(8 * 1024)
           buffer = 8 KB
           let 100 MB file
           Server
           ↓
           8 KB
           ↓
           8 KB
           ↓
            */

         val buffer = ByteArray(8*1024)

         /**read  from buffer util input stream */
               while (true){
                   val read = inputStream.read(buffer)
                   /**if read != -1  write to output stream*/
                   if(read==-1){
                     return
                   }
                   outputStream.write(buffer,0,read)

                   downLoadBytes += read

                   /**handle progress */
                   val progress =
                       if ((totalBytes ?: 0L) > 0) {
                           ((downLoadBytes * 100) / totalBytes!!).toInt()
                       } else {
                           0
                       }
                    progressCallback(progress)

                   /*update database with download bytes */
                   dao.updateProgress(
                       id = downloadEntity.id,
                       bytes = downLoadBytes,
                       total = totalBytes,
                       path = outPutFile.absolutePath,
                       name = outPutFile.name,
                       status = DownloadStatus.DOWNLOADING
                   )

               }

           }
       }




    }

}