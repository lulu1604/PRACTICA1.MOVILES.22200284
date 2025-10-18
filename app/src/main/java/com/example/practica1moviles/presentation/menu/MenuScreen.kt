package com.example.practica1moviles.presentation.menu

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    // 🎨 Fondo degradado
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2196F3),
            Color(0xFF1976D2)
        )
    )

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
            // 🏷️ Título principal
            Text(
                text = "Menú Principal",
                color = Color.White,
                fontSize = 28.sp,
                style = MaterialTheme.typography.headlineMedium
            )

            // 💧 Tarjeta 1 - Agua
            MenuCard(
                title = "Calculadora de Consumo de Agua",
                subtitle = "Recomendación diaria según tu peso y género",
                icon = Icons.Default.LocalDrink,
                color = Color(0xFF4FC3F7)
            ) {
                navController.navigate("agua")
            }

            // 🏃‍♀️ Tarjeta 2 - Actividad Física
            MenuCard(
                title = "Registro de Actividad Física",
                subtitle = "Calcula calorías quemadas por actividad",
                icon = Icons.Default.DirectionsRun,
                color = Color(0xFF81C784)
            ) {
                navController.navigate("actividad")
            }

            // 🚗 Tarjeta 3 - Autos
            MenuCard(
                title = "Catálogo de Autos Deportivos",
                subtitle = "Explora autos de lujo y su valor total",
                icon = Icons.Default.DirectionsCar,
                color = Color(0xFFFFB74D)
            ) {
                navController.navigate("autos")
            }
        }
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
            // 🟢 Icono decorativo
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

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}
