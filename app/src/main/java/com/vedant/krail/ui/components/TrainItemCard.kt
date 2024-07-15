package com.vedant.krail.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vedant.krail.R
import com.vedant.krail.model.Train

@Composable
fun TrainItemCard(train: Train,trainNumber: String,onClick: (Train) -> Unit) {
    Box(
        modifier = Modifier
            .clickable(
                onClick = {onClick(train)},
            )

            .fillMaxSize(),

    ){
        Column(
            modifier = Modifier

                .background(
                    color = Color.White,
                    shape = TicketShape(CornerSize(20.dp))
                )
                .padding(12.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.train),
                    contentDescription = "Train Icon",
                    tint = Color(0xFF007BFF),
                    modifier = Modifier
                        .size(60.dp)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = trainNumber,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = train.name,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(modifier = Modifier.size(16.dp)) {
                        drawCircle(color = Color(0xFF007BFF))
                    }
                    Divider(
                        color = Color(0xFF007BFF).copy(alpha = 0.5f),
                        thickness = 1.dp,
                        modifier = Modifier.weight(1f)
                    )
                    Canvas(modifier = Modifier.size(16.dp)) {
                        drawCircle(color =Color(0xFF007BFF))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Current Status: Delayed by ${train.delayedTime.hours} hrs:${train.delayedTime.minutes} mins",
                color = Color(0xFF007BFF),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal
            )
        }
    }
}



//@Preview
//@Composable
//fun TrainItemCardPreview() {
//    TrainItemCard()
//}