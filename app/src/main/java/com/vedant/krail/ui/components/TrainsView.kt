package com.vedant.krail.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vedant.krail.model.Train

@Composable
fun TrainsView(
    modifier: Modifier = Modifier,
    trains: List<Train>,
    trainNumbers: List<String>,
    onClick: (Train) -> Unit
){
    LazyColumn(modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)) {
        itemsIndexed(trains) { index,train ->
            TrainItemCard(train = train,trainNumber = trainNumbers[index],onClick =onClick)
        }
    }
}