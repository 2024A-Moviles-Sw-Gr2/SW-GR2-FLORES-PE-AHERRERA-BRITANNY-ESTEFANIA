import java.time.LocalDate

data class Pintura(
    val id: Int,
    var titulo: String,
    var artista: Artista,
    var fechaCreacion: LocalDate,
    var esVendida: Boolean,
    var precio: Double
)
