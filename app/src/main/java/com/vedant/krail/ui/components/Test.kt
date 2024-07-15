package com.vedant.krail.ui.components
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vedant.krail.R



@Composable
fun TicketCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(320.dp)
                .background(
                    color = Color(0xFF007BFF),
                    shape = TicketShape(CornerSize(20.dp))
                )
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Next Station",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Chiplun",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.train),
                    contentDescription = "Train Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(horizontal = 8.dp)
                )
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Direction",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "UP(Mumbai)",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold
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
                        drawCircle(color = Color.White)
                    }
                    Divider(
                        color = Color.White.copy(alpha = 0.5f),
                        thickness = 1.dp,
                        modifier = Modifier.weight(1f)
                    )
                    Canvas(modifier = Modifier.size(16.dp)) {
                        drawCircle(color = Color.White)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Current Status 32.0 Mins Delay Arrived Sawantwadi Road",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun TicketShape(cutCornerSize: CornerSize): Shape {
    return RoundedCornerShape(12.dp).copy(
        bottomStart = cutCornerSize,
        bottomEnd = cutCornerSize
    )
}

@Preview
@Composable
fun TicketCardPreview() {
    TicketCard()
}