package com.example.systemdesign.design.instagramFeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.systemdesign.design.instagramFeed.presentation.screen.ProductFeedScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstaGramFeedActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme()
             {
                 ProductFeedScreen()
             }
        }
    }
}