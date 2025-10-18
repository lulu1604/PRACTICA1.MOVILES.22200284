package com.example.practica1moviles.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc_practica.presentation.actividad.ActividadScreen
import com.example.pc_practica.presentation.agua.AguaScreen
import com.example.practica1moviles.presentation.menu.MenuScreen

import com.example.practica1moviles.presentation.autos.AutosScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MenuScreen(navController) }
        composable("agua") { AguaScreen(navController) }
        composable("actividad") { ActividadScreen(navController) }
        composable("autos") { AutosScreen(navController) }
    }
}
