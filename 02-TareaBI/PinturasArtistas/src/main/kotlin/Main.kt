import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

fun main() {
    fun ingresarArtista(){
        println("Ingrese el nombre del artista:")
        val nombre = readLine()!!
        println("Ingrese la fecha de nacimiento (YYYY-MM-DD):")
        val fechaNacimiento = LocalDate.parse(readLine(), DateTimeFormatter.ISO_DATE)
        println("¿Está vivo? (si/no):")
        val esVivo = when (readLine()?.toLowerCase()) { // ? indica que el valor devuelto puede ser nulo
            "si" -> true
            "no" -> false
            else -> false
        }
        println("Ingrese el número de obras:")
        val numObras = readLine()?.toIntOrNull() ?: 0
        println("Ingrese el precio promedio:")
        val precioPromedio = readLine()?.toDoubleOrNull() ?: 0.0
        OperacionesCrud.anadirArtista(nombre, fechaNacimiento, esVivo, numObras, precioPromedio)
        println("Artista creado. Presione Enter para regresar al menú.")
        readLine()
    }
    fun ingresarPintura(){
        println("Ingrese el título de la pintura:")
        val titulo = readLine()!!
        println("Seleccione el ID del artista:")
        OperacionesCrud.leerArtistas().forEach { println("${it.id}. ${it.nombre}") }
        val idArtista = readLine()?.toIntOrNull() ?: 0
        val artista = OperacionesCrud.leerArtistas().find { it.id == idArtista } ?: return
        println("Ingrese la fecha de creación (YYYY-MM-DD):")
        val fechaCreacion = LocalDate.parse(readLine(), DateTimeFormatter.ISO_DATE)
        println("¿Está vendida? (si/no):")
        val esVendida = when (readLine()?.toLowerCase()) { // ? indica que el valor devuelto puede ser nulo
            "si" -> true
            "no" -> false
            else -> false
        }
        println("Ingrese el precio:")
        val precio = readLine()?.toDoubleOrNull() ?: 0.0
        OperacionesCrud.anadirPintura(titulo, artista, fechaCreacion, esVendida, precio)
        println("Pintura creada. Presione Enter para regresar al menú.")
        readLine()
    }
    fun actualizarArtista(){
        println("Seleccione el ID del artista a actualizar:")
        OperacionesCrud.leerArtistas().forEach { println("${it.id}. ${it.nombre}") }
        val id = readLine()?.toIntOrNull() ?: 0
        println("Ingrese el nuevo nombre (dejar en blanco para no cambiar):")
        val nombre = readLine()
        println("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD, dejar en blanco para no cambiar):")
        val fechaNacimiento = readLine()?.takeIf { it.isNotBlank() }?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
        println("¿Está vivo? (true/false, dejar en blanco para no cambiar):")
        val esVivo = readLine()?.takeIf { it.isNotBlank() }?.toBoolean()
        println("Ingrese el nuevo número de obras (dejar en blanco para no cambiar):")
        val numObras = readLine()?.takeIf { it.isNotBlank() }?.toIntOrNull()
        println("Ingrese el nuevo precio promedio (dejar en blanco para no cambiar):")
        val precioPromedio = readLine()?.takeIf { it.isNotBlank() }?.toDoubleOrNull()
        OperacionesCrud.actualizarArtista(id, nombre, fechaNacimiento, esVivo, numObras, precioPromedio)
        println("Artista actualizado. Presione Enter para regresar al menú.")
        readLine()
    }
    fun actualizarPintura(){
        println("Seleccione el ID de la pintura a actualizar:")
        OperacionesCrud.leerPinturas().forEach { println("${it.id}. ${it.titulo}") }
        val id = readLine()?.toIntOrNull() ?: 0
        println("Ingrese el nuevo título (dejar en blanco para no cambiar):")
        val titulo = readLine()
        println("Seleccione el nuevo ID del artista (dejar en blanco para no cambiar):")
        OperacionesCrud.leerArtistas().forEach { println("${it.id}. ${it.nombre}") }
        val idArtista = readLine()?.toIntOrNull()
        val artista = idArtista?.let { OperacionesCrud.leerArtistas().find { it.id == idArtista } }
        println("Ingrese la nueva fecha de creación (YYYY-MM-DD, dejar en blanco para no cambiar):")
        val fechaCreacion = readLine()?.takeIf { it.isNotBlank() }?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
        println("¿Está vendida? (true/false, dejar en blanco para no cambiar):")
        val esVendida = readLine()?.takeIf { it.isNotBlank() }?.toBoolean()
        println("Ingrese el nuevo precio (dejar en blanco para no cambiar):")
        val precio = readLine()?.takeIf { it.isNotBlank() }?.toDoubleOrNull()
        OperacionesCrud.actualizarPintura(id, titulo, artista, fechaCreacion, esVendida, precio)
        println("Pintura actualizada. Presione Enter para regresar al menú.")
        readLine()
    }


    while (true) {
        println("Seleccione una opción:")
        println("1. Crear Artista")
        println("2. Crear Pintura")
        println("3. Ver Artistas")
        println("4. Ver Pinturas")
        println("5. Actualizar Artista")
        println("6. Actualizar Pintura")
        println("7. Eliminar Artista")
        println("8. Eliminar Pintura")
        println("9. Salir")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                ingresarArtista();
            }
            2 -> {
                ingresarPintura();
            }
            3 -> {
                println("Artistas:")
                OperacionesCrud.leerArtistas().forEach { println("Nombre: + ${it.nombre}, Fecha Nacimiento: ${it.fechaNacimiento},Esta vivo: ${it.estaVivo}, Numero obras: ${it.numObras}, Precio promedio de obras ${it.precioPromedio}") }
                println("Presione Enter para regresar al menú.")
                readLine()
            }
            4 -> {
                println("Pinturas:")
                OperacionesCrud.leerPinturas().forEach {
                    println("Título: ${it.titulo}, Artista: ${it.artista.nombre}, Fecha de Creación: ${it.fechaCreacion}, ¿Fué Vendida?: ${if (it.esVendida) "Sí" else "No"}")
                }
                println("Presione Enter para regresar al menú.")
                readLine()
            }
            5 -> {
                actualizarArtista();
            }
            6 -> {
                actualizarPintura();
            }
            7 -> {
                println("Seleccione el ID del artista a eliminar:")
                OperacionesCrud.leerArtistas().forEach { println("${it.id}. ${it.nombre}") }
                val id = readLine()?.toIntOrNull() ?: 0
                OperacionesCrud.borrarArtista(id)
                println("Artista eliminado. Presione Enter para regresar al menú.")
                readLine()
            }
            8 -> {
                println("Seleccione el ID de la pintura a eliminar:")
                OperacionesCrud.leerPinturas().forEach { println("${it.id}. ${it.titulo}") }
                val id = readLine()?.toIntOrNull() ?: 0
                OperacionesCrud.borrarPintura(id)
                println("Pintura eliminada. Presione Enter para regresar al menú.")
                readLine()
            }
            9 -> {
                println("Saliendo...")
                exitProcess(0)
            }
            else -> println("Opción no válida. Presione Enter para regresar al menú.")
        }
    }
}
