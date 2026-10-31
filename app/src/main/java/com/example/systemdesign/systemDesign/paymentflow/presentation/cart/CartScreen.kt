package com.example.systemdesign.systemDesign.paymentflow.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.systemdesign.systemDesign.paymentflow.navigation.Screen

@Composable
fun CartScreen(

) {

    val navController = rememberNavController()
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems by viewModel.cartList.collectAsState()

    val totalAmount = cartItems.sumOf {
        it.price * it.quantity
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        // Toolbar
        Text(
            text = "My Cart (${cartItems.size} items)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        if (cartItems.isEmpty()) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your cart is empty",
                    fontSize = 18.sp
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = cartItems,
                    key = { it.productId }
                ) { item ->

                    CartItemView(
                        item = item,
                        onIncrease = { viewModel.increaseQuantity(item.productId) },
                        onDecrease = { viewModel.decreaseQuantity(item.productId)  },
                        onRemove = { viewModel.deleteCart(item.productId) }
                    )
                }
            }

            CartPriceSummary(
                totalAmount = totalAmount,
                onProceedToPayment = {
                    navController.navigate(Screen.PaymentScreen.route)
                }
            )
        }
    }
}