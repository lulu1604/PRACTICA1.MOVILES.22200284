package com.example.practica1moviles.presentation.autos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.practica1moviles.data.repository.AutoRepository
import java.text.NumberFormat
import java.util.*

@Composable
fun AutosScreen(navController: NavController) {
    val autos = AutoRepository.getAutosDeportivos()
    val total = autos.sumOf { it.precio }
    val format = NumberFormat.getCurrencyInstance(Locale("es", "PE"))

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("🚗 Catálogo de Autos Deportivos", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
            items(autos) { AutoCard(it, format) }
        }
        Spacer(Modifier.height(12.dp))
        Text("Total: ${format.format(total)}", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()) { Text("Volver al Menú") }
    }
}

@Composable
fun AutoCard(auto: com.example.practica1moviles.data.model.Auto, format: NumberFormat) {
    Card(shape = RoundedCornerShape(16.dp)) {
        Row(Modifier.fillMaxWidth().padding(12.dp)) {
            AsyncImage(
                model = auto.imagen,
                contentDescription = auto.modelo,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp).clip(RoundedCornerShape(12.dp))
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(auto.marca, style = MaterialTheme.typography.titleMedium)
                Text(auto.modelo, style = MaterialTheme.typography.bodySmall)
                Text(format.format(auto.precio), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
