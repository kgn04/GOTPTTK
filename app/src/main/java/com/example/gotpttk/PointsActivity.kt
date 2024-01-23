package com.example.gotpttk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gotpttk.ui.theme.GOTPTTKTheme

class PointsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GOTPTTKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PointsAlertDialog(10, this)
                }
            }
        }
    }
}

@Composable
fun PointsAlertDialog(points: Int, activity: ComponentActivity) {
    AlertDialog(
        title = {
            Text(text = "Punkty")
        },
        text = {
            Text(text = "Posiadasz $points punktów.")
        },
        onDismissRequest = { },
        confirmButton = {
            TextButton(
                onClick = { activity.finish() }
            ) {
                Text("OK")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PointsAlertDialogPreview() {
    GOTPTTKTheme {
        PointsAlertDialog(10, ComponentActivity())
    }
}