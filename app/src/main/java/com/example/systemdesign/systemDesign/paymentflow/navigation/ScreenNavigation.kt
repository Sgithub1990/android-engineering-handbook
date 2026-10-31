package com.example.systemdesign.systemDesign.paymentflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.systemdesign.systemDesign.paymentflow.presentation.cart.CartScreen
import com.example.systemdesign.systemDesign.paymentflow.presentation.payment.PaymentScreen
import com.example.systemdesign.systemDesign.paymentflow.presentation.product.ProductsScreen

@Composable
fun ScreenNavigation(navController: NavHostController){
    NavHost(navController, Screen.Product.route) {
        composable(Screen.Product.route) {
          ProductsScreen()
        }

        composable(Screen.CartScreen.route) {
            CartScreen()
        }
        composable(Screen.PaymentScreen.route){
            PaymentScreen()
        }
        }


}