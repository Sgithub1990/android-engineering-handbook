package com.example.systemdesign.android.okhttp

import okhttp3.CertificatePinner

class CertificatePinning {

     fun addCertificate():CertificatePinner{
        val certificatePinning = CertificatePinner.Builder()
            .add("api.example.com","sha256/PRIMARY_PIN")
            .add("api.example.com","sha256/BACKUP_PIN")
            .build()

      return certificatePinning;
    }

}