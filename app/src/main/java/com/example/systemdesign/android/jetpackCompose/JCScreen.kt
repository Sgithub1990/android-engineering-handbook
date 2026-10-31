package com.example.systemdesign.android.jetpackCompose

import android.content.Context
import android.view.LayoutInflater
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.systemdesign.R
import com.example.systemdesign.android.room.StudentEntity
import com.example.systemdesign.design.instagramFeed.data.local.ProductEntity
import com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity
import kotlinx.coroutines.launch


@Composable
fun CounterView() {
    var counter by rememberSaveable { mutableIntStateOf(0) }
    Column(modifier = Modifier) {
        Text(
            text = "$counter"
        )
        Button(onClick = {
            counter++
        }) {
            Text(
                text = "Increase Counter"
            )
        }
        Button(onClick = {
            counter--
        }) {
            Text(
                text = "Decrease Counter"
            )
        }
    }
}

@Composable
fun SideEffectApi() {
    val scope = rememberCoroutineScope()
    val viewModel = hiltViewModel<JCViewModel>()
    var counter by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.apiCall()
    }

    Column {
        Button(onClick = {
            scope.launch {
                viewModel.apiCall()
            }
        }) {
            Text("Call Api")
        }
    }

    DisposableEffect(Unit) {
        /* set up code */
        onDispose {
            /* clean up code */
        }
    }

    val isEven by remember {
        derivedStateOf {
            counter % 2 == 0
        }
    }
}

@Composable
fun DerivedStateExampleWithListScroll() {
    val listState = rememberLazyListState()
    val isButtonShow = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    LazyColumn(state = listState) {
        items(100) {
            Text("Items $it")
        }
    }

    if (isButtonShow.value) {
        Button(onClick = {}) {
            Text("Button")
        }
    }
}

@Composable
fun SnackBar() {
    val snackBarHost = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        snackBarHost.showSnackbar("message", "Ok")
    }
}

/* Composition local example */
val LocalUser = compositionLocalOf<String> { "" }

@Composable
fun Profile() {
}

@Composable
fun Setting() {
    val name = LocalUser.current
}

@Composable
fun Home() {
    Profile()
    Setting()
}

@Composable
fun CompositionLocalUse() {
    CompositionLocalProvider(LocalUser provides String()) {
        Home()
    }
}

/**
collectAsState -> convert kotlin flow to compose state
snapSotFlow -> convert compose state to kotlin flow
*/
@Composable
fun SnapSotFlowExample() {
    val counter = remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        snapshotFlow { counter.value }.collect {
        }
    }
}

/* stable key Example */
@Composable
fun StableKey() {
    val students = remember { mutableListOf<StudentEntity>() }
    LazyColumn {
        items(items = students, key = { student -> student.id }) {
            Text(text = "Student")
        }
    }
}
