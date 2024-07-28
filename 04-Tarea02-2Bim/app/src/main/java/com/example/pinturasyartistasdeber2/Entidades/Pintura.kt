package com.example.pinturasyartistasdeber2.Entidades

import java.time.LocalDate

data class Pintura(
    val id: Int,
    var titulo: String,
    var fechaCreacion: String,
    var esVendida: Boolean,
    var precio: Double
){
    override fun toString(): String {
        return "Título=$titulo - Fecha Creación=$fechaCreacion - Fue Vendida =$esVendida, Precio =$precio"
    }
}
