import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object OperacionesCrud {
    private val artistasFile = File("artistas.txt")
    private val pinturasFile = File("pinturas.txt")

    private val artistas = mutableListOf<Artista>()
    private val pinturas = mutableListOf<Pintura>()

    init {
        cargaDatos()
    }

    private fun cargaDatos() {
        // Cargar artistas desde el archivo
        if (artistasFile.exists()) {
            artistasFile.forEachLine { linea -> //itera sobre cada linea del archivo
                val palabras = linea.split(",") //separa las palabras por coma
                if (palabras.size == 6) {
                    val id = palabras[0].toInt()
                    val nombre = palabras[1]
                    val fechaNacimiento = LocalDate.parse(palabras[2], DateTimeFormatter.ISO_DATE) //parsea la fecha
                    val estaVivo = palabras[3].toBoolean()
                    val numObras = palabras[4].toInt()
                    val precioPromedio = palabras[5].toDouble()

                    artistas.add(
                        Artista(
                            id,
                            nombre,
                            fechaNacimiento,
                            estaVivo,
                            numObras,
                            precioPromedio
                        )
                    )
                } else {
                    println("Error al cargar el archivo de artistas: $linea")
                }
            }
        }

        // Cargar pinturas desde el archivo
        if (pinturasFile.exists()) {
            pinturasFile.forEachLine { linea ->
                val palabras = linea.split(",")
                if (palabras.size == 6) {
                    println("Palabras: ${palabras.size}")
                    val id = palabras[0].toInt()
                    val titulo = palabras[1]
                    val idArtista = palabras[2].toInt()
                    val fechaCreacion = LocalDate.parse(palabras[3], DateTimeFormatter.ISO_DATE)
                    val esVendida = palabras[4].toBoolean()
                    val precio = palabras[5].toDouble()

                    val artista = artistas.find { it.id == idArtista }
                    if (artista != null) {
                        pinturas.add(
                            Pintura(
                                id,
                                titulo,
                                artista,
                                fechaCreacion,
                                esVendida,
                                precio
                            )
                        )
                    } else {
                        println("Artista no encontrado para la pintura: $linea")
                    }
                } else {
                    println("Error al cargar el arvhivo de artistas: $linea")
                }
            }
        }
    }



    private fun guardarDatos() {

        artistasFile.writeText(artistas.joinToString("\n") { "${it.id},${it.nombre},${it.fechaNacimiento},${it.estaVivo},${it.numObras},${it.precioPromedio}" })

        pinturasFile.writeText(pinturas.joinToString("\n") { "${it.id},${it.titulo},${it.artista.id},${it.fechaCreacion},${it.esVendida},${it.precio}" })
    }

    fun anadirArtista(nombre: String, fechaNacimiento: LocalDate, esVivo: Boolean, numObras: Int, precioPromedio: Double) {
        val nuevoArtista = Artista(artistas.size + 1, nombre, fechaNacimiento, esVivo, numObras, precioPromedio)
        artistas.add(nuevoArtista)
        guardarDatos()
    }

    fun anadirPintura(titulo: String, artista: Artista, fechaCreacion: LocalDate, esVendida: Boolean, precio: Double) {
        val nuevaPintura = Pintura(pinturas.size + 1, titulo, artista, fechaCreacion, esVendida, precio)
        pinturas.add(nuevaPintura)
        guardarDatos()
    }

    fun leerArtistas(): List<Artista> {
        return artistas
    }

    fun leerPinturas(): List<Pintura> {
        return pinturas
    }

    fun actualizarArtista(id: Int, nombre: String?, fechaNacimiento: LocalDate?, estaVivo: Boolean?, numObras: Int?, precioPromedio: Double?) {
        val indice = artistas.indexOfFirst { it.id == id }
        if (indice != -1) { // si el indice no se encuentra no cambia nada
            val artista = artistas[indice]

            if (nombre != null) artista.nombre = nombre
            if (fechaNacimiento != null) artista.fechaNacimiento = fechaNacimiento
            if (estaVivo != null) artista.estaVivo = estaVivo
            if (numObras != null) artista.numObras = numObras
            if (precioPromedio != null) artista.precioPromedio = precioPromedio

            artistas[indice] = artista
            guardarDatos()
        }
    }


    fun actualizarPintura(id: Int, titulo: String?, artista: Artista?, fechaCreacion: LocalDate?, esVendida: Boolean?, precio: Double?) {
        val indice = pinturas.indexOfFirst { it.id == id }
        if (indice != -1) {
            val pintura = pinturas[indice]

            // Solo actualizar los campos que no son nulos
            if (titulo != null) pintura.titulo = titulo
            if (artista != null) pintura.artista = artista
            if (fechaCreacion != null) pintura.fechaCreacion = fechaCreacion
            if (esVendida != null) pintura.esVendida = esVendida
            if (precio != null) pintura.precio = precio

            // Actualizar la lista con la pintura modificada
            pinturas[indice] = pintura
            guardarDatos()
        }
    }


    fun borrarArtista(id: Int) {
        val artista = artistas.find { it.id == id }
        if (artista != null) {
            artistas.remove(artista)
            pinturas.removeAll { it.artista == artista }
            guardarDatos()
        }
    }

    fun borrarPintura(id: Int) {
        val pintura = pinturas.find { it.id == id }
        if (pintura != null) {
            pinturas.remove(pintura)
            guardarDatos()
        }
    }
}
