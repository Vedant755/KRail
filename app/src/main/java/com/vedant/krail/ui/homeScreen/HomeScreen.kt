package com.vedant.krail.ui.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vedant.krail.R
import com.vedant.krail.model.Train
import com.vedant.krail.ui.components.TrainsView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    onClick:(Train)-> Unit
) {
    val trains by homeScreenViewModel.trains.observeAsState(emptyList())
    val trainNumber by homeScreenViewModel.trainNumber.observeAsState(emptyList())
    val isRefreshing by homeScreenViewModel.isRefreshing.observeAsState(false)
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { homeScreenViewModel.refreshTrains() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                TrainsView(
                    modifier = Modifier, trains = trains,trainNumbers = trainNumber,
                    onClick = onClick
                )
            }
        }
    }
}



