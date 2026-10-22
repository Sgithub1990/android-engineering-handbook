package com.example.systemdesign.design.instagramFeed.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.systemdesign.design.instagramFeed.presentation.ProductViewModel

@Composable
fun ProductFeedScreen()
{
    val  viewModeL : ProductViewModel = hiltViewModel()

    val products = viewModeL.products.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(
            count = products.itemCount,
            key = products.itemKey { product ->
                product.id
            }
        ) { index ->

            products[index]?.let { product ->
                ProductFeedItem(product,{
                    viewModeL.syncLike(product.id)
                })
            }
        }

        /*loading next page*/

        when(products.loadState.append){
            is LoadState.Error ->{
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
            is LoadState.NotLoading -> {

         }
        }
    }

}