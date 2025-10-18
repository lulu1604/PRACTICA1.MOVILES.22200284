package com.example.practica1moviles.data.repository

import com.example.practica1moviles.data.model.Auto

object AutoRepository {
    fun getAutosDeportivos(): List<Auto> {
        return listOf(
            Auto("Ferrari", "488 Pista", 1300000.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQml5DBpjEAz_qw6LbEXhemQ7hrn07pInAWZg&s"),
            Auto("Lamborghini", "Huracán EVO", 1200000.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHBMI1jhJpZvoZZ7mTkeNc9LUqTuwx_k4Xgg&s"),
            Auto("Porsche", "911 GT3 RS", 900000.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAris0dZnB5floyTnf8ye_gPDk9IBVBMRWMQ&s"),
            Auto("McLaren", "720S", 1100000.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRWQeJvO4NblJZFF5l4CfTbmROBXSqJ9cvXw&s"),
            Auto("Aston Martin", "Vantage", 800000.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT554aZ5qbgOfri4b1sH_RnXDxgD9WvbIhB8w&s")
        )
    }
}
