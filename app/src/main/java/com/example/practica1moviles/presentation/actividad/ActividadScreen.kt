package com.example.pc_practica.presentation.actividad

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActividadScreen(navController: NavController) {

    // ---- VARIABLES DE ESTADO ----
    val actividades = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
    var actividad by remember { mutableStateOf("Correr") }
    var duracion by remember { mutableStateOf("") }
    var intensidad by remember { mutableStateOf("Media") }
    var expanded by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()  // ✅ para usar launch { } en lugar de LaunchedEffect

    val caloriasBase = mapOf(
        "Correr" to 10,
        "Caminar" to 5,
        "Nadar" to 8,
        "Ciclismo" to 7,
        "Yoga" to 4
    )

    // ---- UI ----
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize()
        ) {
            Text(
                "🏃 Registro de Actividad Física",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            // --- Dropdown para tipo de actividad ---
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(
                    value = actividad,
                    onValueChange = {},
                    label = { Text("Tipo de actividad") },
                    readOnly = true,
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    actividades.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                actividad = it
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // --- Duración ---
            OutlinedTextField(
                value = duracion,
                onValueChange = { duracion = it },
                label = { Text("Duración (min)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            // --- Intensidad ---
            Text("Intensidad:")
            listOf("Baja", "Media", "Alta").forEach { opt ->
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    RadioButton(
                        selected = intensidad == opt,
                        onClick = { intensidad = opt }
                    )
                    Text(opt)
                }
            }

            Spacer(Modifier.height(16.dp))

            // --- Botón Registrar ---
            Button(
                onClick = {
                    val min = duracion.toIntOrNull()
                    if (min == null || min <= 0) {
                        resultado = ""
                        scope.launch {
                            snackbarHostState.showSnackbar("⚠️ Ingresa una duración válida")
                        }
                    } else {
                        val base = caloriasBase[actividad] ?: 0
                        val factor = when (intensidad) {
                            "Baja" -> 0.8
                            "Alta" -> 1.2
                            else -> 1.0
                        }
                        val total = base * min * factor
                        resultado =
                            "🔥 Has quemado %.1f calorías en $min minutos de $actividad.".format(total)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }

            Spacer(Modifier.height(16.dp))

            // --- Resultado ---
            Text(resultado, style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.weight(1f))

            // --- Botón volver ---
            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Menú")
            }
        }
    }
}
