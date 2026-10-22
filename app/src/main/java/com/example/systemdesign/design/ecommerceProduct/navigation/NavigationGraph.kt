package com.example.systemdesign.design.ecommerceProduct.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.systemdesign.design.ecommerceProduct.presentation.screens.cart.CartScreen
import com.example.systemdesign.design.ecommerceProduct.presentation.screens.productList.EComProductList
import com.example.systemdesign.design.ecommerceProduct.presentation.screens.search.SearchScreen

@Composable
fun NavigationGraph(navHostController: NavHostController){

    NavHost(navController = navHostController, startDestination = Screen.ProductList){

        composable<Screen.ProductList>{
            EComProductList({
                navHostController.navigate(Screen.CartList)
            },{})
        }
        composable<Screen.CartList> {
            CartScreen()
        }

        composable<Screen.SearchScreen> {
           SearchScreen()
        }


    }

}