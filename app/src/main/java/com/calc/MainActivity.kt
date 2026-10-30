package com.calc.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Fondo = Color(0xFF0E0E10)
private val Tecla = Color(0xFF1C1C1F)
private val TeclaSuave = Color(0xFF2A2A2E)
private val Acento = Color(0xFFFF9F0A)
private val TextoClaro = Color(0xFFF5F5F7)
private val TextoGris = Color(0xFF8E8E93)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaCalculadora()
        }
    }
}

@Composable
fun PantallaCalculadora() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Pantalla()
        Teclado()
    }
}

@Composable
private fun Pantalla() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "128 × 4",
            color = TextoGris,
            fontSize = 22.sp,
            maxLines = 1
        )
        Text(
            text = "512",
            color = TextoClaro,
            fontSize = 72.sp,
            fontWeight = FontWeight.Light,
            maxLines = 1
        )
    }
}

@Composable
private fun Teclado() {
    val filas = listOf(
        listOf("C", "±", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "−"),
        listOf("1", "2", "3", "+"),
        listOf("0", ",", "=")
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        filas.forEach { fila ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                fila.forEach { etiqueta ->
                    Tecla(
                        etiqueta = etiqueta,
                        modificador = if (etiqueta == "0") Modifier.weight(2f) else Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun Tecla(etiqueta: String, modificador: Modifier = Modifier) {
    val esOperador = etiqueta in listOf("÷", "×", "−", "+", "=")
    val esFuncion = etiqueta in listOf("C", "±", "%")

    val color = when {
        esOperador -> Acento
        esFuncion -> TeclaSuave
        else -> Tecla
    }
    val colorTexto = when {
        esOperador -> Color(0xFF0E0E10)
        esFuncion -> Acento
        else -> TextoClaro
    }

    Surface(
        modifier = modificador.fillMaxHeight(),
        shape = RoundedCornerShape(26.dp),
        color = color
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = etiqueta,
                color = colorTexto,
                fontSize = 28.sp,
                fontWeight = if (esOperador) FontWeight.Medium else FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }
    }
}
