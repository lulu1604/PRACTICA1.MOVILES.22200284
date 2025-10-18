package com.example.practica1moviles.presentation.agua

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica1moviles.data.model.Agua
import com.example.practica1moviles.data.repository.AguaRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AguaScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("Sin especificar") }
    var expanded by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(Modifier.padding(padding).padding(24.dp).fillMaxSize()) {
            Text("💧 Calculadora de Consumo de Agua", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(value = nombre, onValueChange = { nombre = it },
                label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = peso, onValueChange = { peso = it },
                label = { Text("Peso corporal (kg)") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(value = genero, onValueChange = {},
                    label = { Text("Género") }, readOnly = true,
                    modifier = Modifier.menuAnchor().fillMaxWidth())
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    listOf("Masculino", "Femenino", "Sin especificar").forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = {
                            genero = it; expanded = false
                        })
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                val pesoNum = peso.toDoubleOrNull()
                if (nombre.isBlank() || pesoNum == null || pesoNum !in 5.0..200.0) {
                    resultado = ""
                    scope.launch { snackbarHostState.showSnackbar("⚠️ Verifica los campos ingresados") }
                } else {
                    val litros = AguaRepository.calcularLitrosRecomendados(
                        Agua(nombre, pesoNum, genero)
                    )
                    resultado = "$nombre debe beber aproximadamente %.2f L de agua al día".format(litros)
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Calcular") }

            Spacer(Modifier.height(16.dp))
            Text(resultado)
            Spacer(Modifier.weight(1f))
            OutlinedButton(onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()) { Text("Volver al Menú") }
        }
    }
}
