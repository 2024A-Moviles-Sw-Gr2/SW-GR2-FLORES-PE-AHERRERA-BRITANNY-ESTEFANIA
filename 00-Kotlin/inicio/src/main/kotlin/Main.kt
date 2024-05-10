import java.util.*

fun main(){
    println("Hola mundo")
    // Inmutables (No se pueden re asignar "=")
    val inmutable: String = "Brit"
    //inmutable = "Mera" // ERROR!

    // Mutables
    var mutable: String = "Flores"
    mutable = "Brit" // OK

    // val > var

    // Duck Typing
    var ejemploVariable = " Brit Flores "
    val edadEjemplo: Int = 12
    ejemploVariable.trim() // Para borra espacios en blanco
    // ejemploVariable = edadEjemplo // ERROR! tipo incorrecto

    // Variables primitivas
    val nombre: String = "Brit"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true
    // clases en Java
    val fechaNacimiento: Date = Date()

    // When (Switch)
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") ->{
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else ->{
            println("No sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No" // if else chiquito
    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)
// Named parameters
// calcularSueldo (sueldo, tasa, bonoEspecial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)


}

// void -> Unit
fun imprimirNombre (nombre:String): Unit{
    println("Nombre: ${nombre}") // Template Strings
}
fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial: Double? = null // Opcional (nullable)
    // variable? -> "?" Es Nullable (osea que puede en algun momento ser nulo)
):Double {
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date -> Date? (nullable)
    if (bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }

}
abstract class Numeros(
    //Caso 1 Parametro normal
    // uno: Int,

    //Caso 2 Parametro y propiedad atributo
    // private var uno: Int
    protected val numeroUno: Int,
    protected val numeroDos: Int,
){
    init{
        this.numeroUno
        this.numeroDos
        print("Inicializando")
    }
}
abstract class NumerosJava
{
    protected val numeroUno:Int
    private val numeroDos:Int
    constructor(
        uno:Int,
        dos:Int
    ) {
        this.numeroUno = uno
        this.numeroDos = dos
        print("Inicializando")
    }
}

class Suma(
    unoParametro:Int,
    dosParametro:Int,
): Numeros(
    unoParametro,
    dosParametro
){
    public val soyPublicoExplicito: String = "Explicicto"
    val soyPublicoImplicito:String = "Implicito"
    init {
        this.numeroUno
        this.numeroDos
        numeroUno
        numeroDos
        this.soyPublicoExplicito
        soyPublicoImplicito
    }
    public fun sumar(): Int {
        val total = numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }
    companion object{
        val pi=3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num*num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }
    }
}

