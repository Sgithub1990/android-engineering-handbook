package com.example.systemdesign.design.imageLoadingLibrary.data.decoder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

class ImageDecoder {

    fun decode(byteArray: ByteArray): Bitmap{

       return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }


    fun decode(file: File): Bitmap{
       return BitmapFactory.decodeFile(file.absolutePath)
    }

}