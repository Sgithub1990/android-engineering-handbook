package com.example.systemdesign.design.imageLoadingLibrary.data.downSampling

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

class DownSampling {

    fun decode(
        file: File,
        requiredHeight: Int,
        requiredWith: Int
      ):Bitmap{

        /** we calculate original size without creating the Bitmap. */

       val option : BitmapFactory.Options = BitmapFactory.Options()
         option.inJustDecodeBounds = true

        BitmapFactory.decodeFile(
           file.absolutePath,
            option
        )

        val height = option.outHeight
        val width = option.outWidth

       /** Calculate how much we should reduce*/
        val sampleSize = calculateSampleSize(width,height,requiredWith,requiredHeight)

       /** Tell Android to downsample*/
        option.inSampleSize = sampleSize;

        /** Now actually create the Bitmap*/
        option.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(
            file.absolutePath,
            option)

    }

    fun calculateSampleSize(
        width: Int,
        height: Int,
        requiredWidth: Int,
        requiredHeight: Int
    ): Int{
      var sampleSize = 1;

      if(width>requiredWidth || height>requiredHeight){

          val halfHeight = height/2
          val halfWidth = width/2

          while(halfHeight/sampleSize> requiredHeight && halfWidth/sampleSize>requiredWidth){
              sampleSize *= 2
          }

      }

        return  sampleSize;

    }




}