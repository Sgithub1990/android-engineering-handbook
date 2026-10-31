package com.example.systemdesign.systemDesign.paymentflow.navigation

sealed class Screen( val route: String) {
    data object Product: Screen("product")
    data object CartScreen: Screen("cart")
    data object PaymentScreen: Screen("payment")

}