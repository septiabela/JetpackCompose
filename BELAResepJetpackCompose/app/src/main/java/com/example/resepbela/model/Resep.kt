package com.example.resepbela.model

data class Resep(
    val id: Int,
    val name: String,
    val image: Int,
    val description: String,
    val rating: Double,
    var isFavorite: Boolean = false
)