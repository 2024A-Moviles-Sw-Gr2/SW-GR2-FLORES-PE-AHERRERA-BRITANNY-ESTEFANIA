package com.example.pinturasyartistasdeber2.Entidades

import java.time.LocalDate

data class Artista(
    val id: Int,
    var nombre: String,
    var fechaNacimiento: String,
    var estaVivo: Boolean,
    var numObras: Int,
    var precioPromedio: Double
){
    override fun toString(): String {
        return "Nombre=$nombre - Fecha Nacimiento=$fechaNacimiento - Número de Obras=$numObras - Precio Promedio=$precioPromedio"
    }
}


