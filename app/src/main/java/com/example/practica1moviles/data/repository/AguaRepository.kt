package com.example.practica1moviles.data.repository

import com.example.practica1moviles.data.model.Agua

object AguaRepository {
    fun calcularLitrosRecomendados(agua: Agua): Double {
        val factor = when (agua.genero) {
            "Masculino" -> 1.02
            "Femenino" -> 1.01
            else -> 1.00
        }
        return agua.peso * 0.035 * factor
    }
}
