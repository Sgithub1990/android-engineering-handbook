package com.example.systemdesign.android.room.sqlcipher

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import net.zetetic.database.sqlcipher.driver.SQLCipherDriver
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object KeyStoreManager {
    const val KEY_ALISE = "my_secret_key"

    fun getORCreateSecureKey(): SecretKey{
        /*Get KeyStore*/
         val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore")
         keyStore.load(null)

        /*check and get if key present */
        if(keyStore.containsAlias("KEY_ALISE")){
          return  keyStore.getKey(KEY_ALISE,null)as SecretKey
        }

        /* create key generator with keyProperties*/
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,"AndroidKeyStore")

        /*key generatorParamSpec with purpose*/
         val paramSpec = KeyGenParameterSpec.Builder(KEY_ALISE,KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()

         /*init kiy generator*/
         keyGenerator.init(paramSpec)

        return keyGenerator.generateKey()

    }

    fun encryptedKey(token: String):EncryptedData{
        /*create cipher*/
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")

        /*init cipher with encryption mode and secrete key */
        cipher.init(Cipher.ENCRYPT_MODE,getORCreateSecureKey())

        /*perform the cipher operation.*/
       val encryptedData  = cipher.doFinal(token.toByteArray())

        /*get iv*/
        val iv = cipher.iv

        return EncryptedData(encryptedData,iv)
    }


    fun decryptedKey(encyptedData: EncryptedData): String{
        /*create cipher*/
        val cipher = Cipher.getInstance("AES/GCM")

        /*create spec*/
         val spec = GCMParameterSpec(128,encyptedData.iv)

        /*init cipher with encryption mode , secrete key and spec*/
         cipher.init(Cipher.DECRYPT_MODE,getORCreateSecureKey(),spec)

        /*perform the cipher operation.*/
         val finalData = cipher.doFinal(encyptedData.cipherText)

       return String(finalData)

     }


    data class EncryptedData(
     val cipherText: ByteArray,
     val iv:ByteArray
    )


  val factory = SupportOpenHelperFactory("s".toByteArray())

  val driver = SQLCipherDriver("s".toByteArray(),null,null)

}