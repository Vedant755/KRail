package com.vedant.krail.ui.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vedant.krail.ui.components.SearchBar
import com.vedant.krail.ui.components.TrainItemCard

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val trainResponse by searchViewModel.trainResponse.observeAsState()

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = searchQuery,
                onValueChange = { value ->
                    searchQuery = value
                    if (value.length == 5) {
                        searchViewModel.fetchTrain(value)
                    }
                },
                onClear = { searchQuery = "" }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                trainResponse == null -> Text("Enter the five digit train number to search")
                trainResponse!!.isSuccess -> {
                    val trainDetails = trainResponse!!.getOrNull()?.trainDetails?.values?.firstOrNull()
                    TrainItemCard(train = trainDetails!!, trainNumber = trainResponse!!.getOrNull()?.trainDetails?.keys?.firstOrNull()!!, onClick = {})
                }
                trainResponse!!.isFailure -> {
                    Text("Error: ${trainResponse!!.exceptionOrNull()?.message}")
                }
            }
        }
    }
}
