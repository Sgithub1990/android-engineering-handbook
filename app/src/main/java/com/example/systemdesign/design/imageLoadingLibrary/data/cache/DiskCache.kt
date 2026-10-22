package com.example.systemdesign.design.imageLoadingLibrary.data.cache

import android.content.Context
import java.io.File

class DiskCache(private val context: Context) {

    val chachDirectory = File(context.cacheDir, "image")

    init {

        if(!chachDirectory.exists()){
            chachDirectory.mkdir()
        }
    }


    fun get(url: String): File?{

        val file = File(chachDirectory,url)

        if(file.exists()){
            return file
        }
        return null;
    }

    fun put(url: String,byteArray: ByteArray): File{
        val file = File(chachDirectory,url)
        if(file.exists()){
            file.writeBytes(byteArray)
        }

        return file
    }


}