package com.example.systemdesign.android.flow

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun FlowScreen() {
    val viewModel: LearnFlowViewModel = hiltViewModel()
    val state = viewModel.getStateFlow().collectAsStateWithLifecycle(0)
}