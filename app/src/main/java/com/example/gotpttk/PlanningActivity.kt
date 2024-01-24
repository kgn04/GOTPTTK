package com.example.gotpttk

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                    PlanningView(this, Graph())
                }
            }
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun PlanningView(activity: ComponentActivity, graph: Graph) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    var isPlanned by remember { mutableStateOf(false) }
    var chosen by remember {
        mutableStateOf(graph.chosen)
    }
    var availableToChoose by remember {
        mutableStateOf(graph.availableToChoose)
    }
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
                    painterResource(id = R.mipmap.mapa),
                    contentScale = ContentScale.FillBounds)

        })
    {
        coords.forEachIndexed {
            index, pair ->
            val color: Color = if (chosen.contains(index))
                Color.Blue
            else if (availableToChoose.contains(index))
                Color.Red
            else
                Color.Gray
            TextButton(
                onClick = {
                    graph.chooseStop(index)
                    chosen = graph.chosen
                    availableToChoose = graph.availableToChoose
                          },
                modifier = Modifier
                    .offset(x = (pair.first*screenWidth/390).dp, y = (pair.second*screenHeight/850).dp)) {
                Text(text = "X",
                    color = color,
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp
                )
            }
        }
        val mContext = LocalContext.current
        Button(
            modifier = Modifier.offset(x = (screenWidth * 0.05).dp, y = (screenHeight * 0.85).dp),
            onClick = {
                if (chosen.size < 2)
                    Toast.makeText(mContext, "Nie wybrano trasy.", Toast.LENGTH_LONG).show()
                else
                    isPlanned = true
            }
        ) {
            Text(text = "Potwierdź")
        }
        if (isPlanned) {
            AlertDialog(
                title = {
                    Text(text = "Podsumowanie")
                },
                text = {
                    Text(
                        text = "Długość wędrówki:          ${graph.summaryLength} m\n" +
                                "Suma podejść:                 ${graph.summaryUphills} m\n" +
                                "Punkty do zdobycia:        ${graph.points}"
                    )
                },
                onDismissRequest = {

                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            isPlanned = false
                            activity.finish()
                            Toast.makeText(mContext, "Wędrówka zaplanowana pomyślnie.", Toast.LENGTH_LONG).show()
                        }
                    ) {
                        Text("Zaplanuj")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { isPlanned = false }
                    ) {
                        Text("Anuluj")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanningViewPreview() {
    GOTPTTKTheme {
        PlanningView(ComponentActivity(), Graph())
    }
}