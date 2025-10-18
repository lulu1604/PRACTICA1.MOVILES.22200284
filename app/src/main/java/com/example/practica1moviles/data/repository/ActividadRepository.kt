package com.example.practica1moviles.data.repository

import com.example.practica1moviles.data.model.Actividad

object ActividadRepository {

    private val caloriasPorMinuto = mapOf(
        "Correr" to 10,
        "Caminar" to 5,
        "Nadar" to 8,
        "Ciclismo" to 7,
        "Yoga" to 4
    )

    fun calcularCalorias(actividad: Actividad): Double {
        val base = caloriasPorMinuto[actividad.tipo] ?: 0
        val factor = when (actividad.intensidad) {
            "Baja" -> 0.8
            "Alta" -> 1.2
            else -> 1.0
        }
        return base * actividad.duracion * factor
    }
}
