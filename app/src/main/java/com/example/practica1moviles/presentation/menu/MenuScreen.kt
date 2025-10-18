package com.example.practica1moviles.presentation.menu



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Menú Principal", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navController.navigate("agua") }, modifier = Modifier.fillMaxWidth()) {
            Text("💧 Calculadora de Consumo de Agua")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = { navController.navigate("actividad") }, modifier = Modifier.fillMaxWidth()) {
            Text("🏃 Registro de Actividad Física")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = { navController.navigate("autos") }, modifier = Modifier.fillMaxWidth()) {
            Text("🚗 Catálogo de Autos Deportivos")
        }
    }
}
