import java.time.LocalDate

data class Artista(
    val id: Int,
    var nombre: String,
    var fechaNacimiento: LocalDate,
    var estaVivo: Boolean,
    var numObras: Int,
    var precioPromedio: Double
)

