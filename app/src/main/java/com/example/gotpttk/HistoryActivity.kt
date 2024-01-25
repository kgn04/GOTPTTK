package com.example.gotpttk

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gotpttk.ui.theme.GOTPTTKTheme
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter

data class Wycieczka(
    val nazwa: String,
    val dataStart: String,
    val dataKoniec: String,
    val punkty: Int,
    val mapaResId: Int
)

class HistoryActivity : ComponentActivity() {
    // Пример коллекции походов
    val wycieczki = listOf(
        Wycieczka("Wyprawa 1", "01.01.2024", "10.01.2024", 50, R.drawable.mapa1),
        Wycieczka("Wyprawa 2", "15.01.2024", "20.01.2024", 40, R.drawable.mapa2),
        Wycieczka("Wyprawa 3", "01.01.2024", "10.01.2024", 50, R.drawable.mapa3),
        Wycieczka("Wyprawa 4", "15.01.2024", "20.01.2024", 40, R.drawable.mapa1),
        Wycieczka("Wyprawa 5", "01.01.2024", "10.01.2024", 50, R.drawable.mapa2),
        Wycieczka("Wyprawa 6", "15.01.2024", "20.01.2024", 40, R.drawable.mapa3),
        Wycieczka("Wyprawa 7", "01.01.2024", "10.01.2024", 50, R.drawable.mapa1),
        Wycieczka("Wyprawa 8", "15.01.2024", "20.01.2024", 40, R.drawable.mapa2),
        Wycieczka("Wyprawa 9", "01.01.2024", "10.01.2024", 50, R.drawable.mapa3),
        Wycieczka("Wyprawa 10", "15.01.2024", "20.01.2024", 40, R.drawable.mapa1),
        Wycieczka("Wyprawa 11", "01.01.2024", "10.01.2024", 50, R.drawable.mapa2),
        Wycieczka("Wyprawa 12", "15.01.2024", "20.01.2024", 40, R.drawable.mapa3),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Установка макета для активности
        setContent {
            GOTPTTKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WycieczkiList(wycieczki)
                }
            }
        }
    }
}

@Composable
fun WycieczkiList(wycieczki: List<Wycieczka>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(wycieczki) { wycieczka ->
            WycieczkaItem(wycieczka)
        }
    }
}

@Composable
fun WycieczkaItem(wycieczka: Wycieczka) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showDialog = true
            }
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = wycieczka.mapaResId,
                builder = {
                    crossfade(true) // Добавьте анимацию замещения, если нужно
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        // Другие детали элемента списка, если они необходимы
        Text(text = "Nazwa: ${wycieczka.nazwa}")
        Text(text = "Data rozpoczęcia: ${wycieczka.dataStart}")
        Text(text = "Data zakończenia: ${wycieczka.dataKoniec}")
        Text(text = "Punkty: ${wycieczka.punkty}")

        // Dialog для отображения фотографии в полном размере
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "Sczegóły wycieczki")
                },
                text = {
                    Image(
                        painter = rememberImagePainter(
                            data = wycieczka.mapaResId,
                            builder = {
                                crossfade(true)
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Закрыть")
                    }
                }
            )
        }
    }
}
