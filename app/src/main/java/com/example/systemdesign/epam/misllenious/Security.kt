package com.example.systemdesign.epam.misllenious

/**
🔥Secure storage — where should tokens be stored?
🔸 KetStore
1-create key which encrypt and decrypt token
2- store encrypted key in datastore
3-Read the encrypted token from storage.
4-Retrieve the encryption key from Android Keystore.
5-Decrypt the token.
Attach it to the API request's Authorization header.

* */

/**
🔥Top Android security

🔸Secure token storage
 1- Use Android Keystore to protect encryption keys.
 2-Never hardcode secrets in source code or commit them to Git.

🔸Secure network communication
 1- Use HTTPS for network communication
 2-Use Certificate pinning for extra layer security

🔸Authentication and authorization
 1- Use short-lived access tokens and refresh-token rotation where supported.

🔸Protect local data
1-Avoid logging tokens, passwords, personal information, or financial data
2-Clear session data on logout, including cached user-specific information.
3- use SQLCipher to securely store sensitive user data in an Android app

🔸Secure Android components
 1- Set android:exported="false" for components that should not be externally accessible
 2-use FLAG_SECURE to avoid screenshots, screen recordings
 In Activity use
window.setFlags(
WindowManager.LayoutParams.FLAG_SECURE,
WindowManager.LayoutParams.FLAG_SECURE
)

 * */


/**
🔥Certificate pinning working
1- Android app creates an HTTPS connection.
2-The server sends its TLS certificate during the TLS handshake.
3-OkHttp validates the certificate and checks it against the configured pin.
4- If validation succeeds, secure communication continues. If pinning fails, the connection is rejected.

 * */

