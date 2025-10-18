package com.example.practica1moviles.presentation.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun MenuScreen(navController: NavController) {

    // 🎨 Fondo con degradado
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF42A5F5),
            Color(0xFF1976D2)
        )
    )

    // 👀 Control de animación
    var visible by remember { mutableStateOf(false) }

    // ⏱️ Al abrir la pantalla, activa la animación
    LaunchedEffect(Unit) {
        delay(150) // pequeño retardo opcional
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            // 🏷️ Título principal con fade
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(700))
            ) {
                Text(
                    text = "Menú Principal",
                    color = Color.White,
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            // 💧 Tarjeta 1 - Agua
            AnimatedCard(
                delay = 200,
                visible = visible,
                title = "Calculadora de Consumo de Agua",
                subtitle = "Recomendación diaria según tu peso y género",
                icon = Icons.Default.LocalDrink,
                color = Color(0xFF4FC3F7)
            ) { navController.navigate("agua") }

            // 🏃‍♀️ Tarjeta 2 - Actividad Física
            AnimatedCard(
                delay = 300,
                visible = visible,
                title = "Registro de Actividad Física",
                subtitle = "Calcula calorías quemadas por actividad",
                icon = Icons.Default.DirectionsRun,
                color = Color(0xFF81C784)
            ) { navController.navigate("actividad") }

            // 🚗 Tarjeta 3 - Autos
            AnimatedCard(
                delay = 400,
                visible = visible,
                title = "Catálogo de Autos Deportivos",
                subtitle = "Explora autos de lujo y su valor total",
                icon = Icons.Default.DirectionsCar,
                color = Color(0xFFFFB74D)
            ) { navController.navigate("autos") }
        }
    }
}

@Composable
fun AnimatedCard(
    delay: Int,
    visible: Boolean,
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    var show by remember { mutableStateOf(false) }

    // ⏱️ Animación escalonada por cada tarjeta
    LaunchedEffect(visible) {
        if (visible) {
            delay(delay.toLong())
            show = true
        }
    }

    AnimatedVisibility(
        visible = show,
        enter = fadeIn(animationSpec = tween(700)) +
                slideInVertically(initialOffsetY = { it / 3 }, animationSpec = tween(700))
    ) {
        MenuCard(title, subtitle, icon, color, onClick)
    }
}

@Composable
fun MenuCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // 🟢 Ícono circular decorativo
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color.copy(alpha = 0.15f), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}
