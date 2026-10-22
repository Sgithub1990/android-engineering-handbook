package com.example.systemdesign.design.ecommerceProduct.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object ProductList : Screen()
    @Serializable
    data object CartList : Screen()

@Serializable
data object SearchScreen: Screen()

}
