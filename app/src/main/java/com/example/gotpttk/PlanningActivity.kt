package com.example.gotpttk

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gotpttk.ui.theme.GOTPTTKTheme

class PlanningActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GOTPTTKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlanningView()
                }
            }
        }
    }
}

@Composable
fun PlanningView() {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val coords: List<Pair<Int, Int>> = listOf(
        Pair(30, 40),
        Pair(118, 30),
        Pair(200, 40),
        Pair(50, 125),
        Pair(150, 130),
        Pair(180, 195),
        Pair(285, 200),
        Pair(210, 245),
        Pair(70, 210),
        Pair(140, 225),
        Pair(90, 290),
        Pair(10, 260),
        Pair(37, 355),
        Pair(75, 403),
        Pair(135, 399),
        Pair(273, 397),
        Pair(200, 430),
        Pair(100, 475),
        Pair(165, 510),
        Pair(187, 665),
        Pair(287, 728),
        Pair(318, 610)

    )
    Box(
        modifier = with (Modifier){
            fillMaxSize()
                .paint(
                    // Replace with your image id
                    painterResource(id = R.mipmap.mapa),
                    contentScale = ContentScale.FillBounds)

        })
    {
        coords.forEach {
            pair ->
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .offset(x = (pair.first*screenWidth/390).dp, y = (pair.second*screenHeight/850).dp)) {
                Text(text = "X",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp
                )
            }
        }
        Button(
            modifier = Modifier.offset(x = (screenWidth * 0.05).dp, y = (screenHeight * 0.85).dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Potwierdź")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanningViewPreview() {
    GOTPTTKTheme {
        PlanningView()
    }
}