package com.example.systemdesign.design.ecommerceProduct.presentation.screens.productList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.systemdesign.design.ecommerceProduct.presentation.EComProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EComProductList(
    onCartClick: () -> Unit,
    onSearchClick: () -> Unit
) {

    val viewModel: EComProductViewModel = hiltViewModel()

    val products = viewModel
        .productList
        .collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Products" ,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                ),
                actions = {

                    // Search
                    IconButton(
                        onClick = onSearchClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }

                    // Cart
                    IconButton(
                        onClick = onCartClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

            items(
                count = products.itemCount,
                key = products.itemKey { it.id }
            ) { index ->

                products[index]?.let { product ->

                    EComProductItem(product) {
                        viewModel.insertProductIntoCart(it)
                    }
                }
            }

            when (products.loadState.append) {

                is LoadState.Error -> {

                    item {
                        Text(
                            text = "Failed to load more products",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                LoadState.Loading -> {

                    item {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is LoadState.NotLoading -> Unit
            }
        }
    }
}