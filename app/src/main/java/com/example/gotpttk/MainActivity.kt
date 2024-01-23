package com.example.gotpttk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gotpttk.ui.theme.GOTPTTKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GOTPTTKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LandingPage()
                }
            }
        }
    }
}

@Composable
fun LandingPage() {
    var expandedButton by remember {
        mutableStateOf("")
    }
    val buttons: Map<String, Map<String, () -> Unit>> = mapOf(
        "Moja książeczka" to mapOf(
            "Punkty" to {},
            "Inne" to {},
        ),
        "Wędrówki" to mapOf(
            "Planuj" to {},
            "Jeszcze inne" to {},
            "Idk" to {}
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "E-KSIĄŻECZKA",
            fontSize = 40.sp
        )
        Text(
            text = "GOT PTTK",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        buttons.forEach { (buttonName, subButtons) ->
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { expandedButton = if (expandedButton == buttonName) "" else buttonName },
                modifier = Modifier
                    .height(80.dp)
                    .width(300.dp)
            ) {
                Text(
                    text = buttonName,
                    fontSize = 30.sp
                )
            }
            if (expandedButton == buttonName) {
                Column {
                    subButtons.forEach { (subButtonName, function) ->
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = function,
                            modifier = Modifier
                                .height(60.dp)
                                .width(200.dp)
                        ) {
                            Text(
                                text = subButtonName,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingPagePreview() {
    GOTPTTKTheme {
        LandingPage()
    }
}