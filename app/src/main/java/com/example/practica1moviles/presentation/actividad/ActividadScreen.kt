package com.example.practica1moviles.presentation.actividad

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica1moviles.data.model.Actividad
import com.example.practica1moviles.data.repository.ActividadRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActividadScreen(navController: NavController) {
    val actividades = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
    var actividad by remember { mutableStateOf("Correr") }
    var duracion by remember { mutableStateOf("") }
    var intensidad by remember { mutableStateOf("Media") }
    var expanded by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(Modifier.padding(padding).padding(24.dp).fillMaxSize()) {
            Text("🏃 Registro de Actividad Física", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(value = actividad, onValueChange = {},
                    label = { Text("Tipo de actividad") }, readOnly = true,
                    modifier = Modifier.menuAnchor().fillMaxWidth())
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    actividades.forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = {
                            actividad = it; expanded = false
                        })
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = duracion, onValueChange = { duracion = it },
                label = { Text("Duración (min)") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Text("Intensidad:")
            listOf("Baja", "Media", "Alta").forEach {
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    RadioButton(selected = intensidad == it, onClick = { intensidad = it })
                    Text(it)
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                val min = duracion.toIntOrNull()
                if (min == null || min <= 0) {
                    resultado = ""
                    scope.launch { snackbarHostState.showSnackbar("⚠️ Ingresa una duración válida") }
                } else {
                    val total = ActividadRepository.calcularCalorias(
                        Actividad(actividad, min, intensidad)
                    )
                    resultado = "🔥 Has quemado %.1f calorías en $min min de $actividad.".format(total)
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Registrar") }

            Spacer(Modifier.height(16.dp))
            Text(resultado)
            Spacer(Modifier.weight(1f))
            OutlinedButton(onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()) { Text("Volver al Menú") }
        }
    }
}
