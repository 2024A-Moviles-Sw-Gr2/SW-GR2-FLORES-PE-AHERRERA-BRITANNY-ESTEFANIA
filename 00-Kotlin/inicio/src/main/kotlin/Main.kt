import java.util.*
import kotlin.collections.ArrayList

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
// Uso de clases
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(0,0)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Arreglos Estaticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
            )
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    print(arregloDinamico)


    val respuestaForEach: Unit = arregloDinamico
        .forEach{valorActual: Int ->
            println("Valor actual: ${valorActual}");
        }
    arregloDinamico.forEach{println("Valor actual (it): ${it}")}

    //MAP: Muta o modifica el arreglo
    //1) se envia el nuevo valor de la iteracion
    //2) Nos devuleve un NUEVO ARREGLO
    //de las iteraciones
    val respuestaMap: List<Double> = arregloDinamico.map {
        valorActual:Int ->
        return@map valorActual.toDouble() + 100.00
    }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it+15 }
    println(respuestaMapDos)

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual:Int ->
            //Expresion o condición
            val mayoresACinco: Boolean = valorActual> 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <=5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual:Int ->
            return@any (valorActual>5)
        }
    println(respuestaAny)
    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
        return@all (valorActual>5)
        }
    println(respuestaAll)

    // Reduce -> Valor acumulado, en kotlin empieza en 0
    // Valor acumulado = 0
    //[1,2,3,4,5] -> Acumular sumar valores del arreglo
    val respuestaReduce : Int = arregloDinamico
        .reduce{acumulado:Int, valorActual:Int ->
            return@reduce (acumulado+valorActual)
        }
    println(respuestaReduce)

    

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
        print("Inicializando \n")
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
        print("Inicializando \n")
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
    constructor( // Primer constructor
        uno:Int?,
        dos:Int
    ):this(
        if(uno==null) 0 else uno,
        dos
    )
    constructor( // Segundo constructor
        uno:Int,
        dos:Int?
    ):this(
        uno,
        if(dos == null) 0 else dos,
    )
    constructor( //tercer constructor
        uno:Int?,
        dos:Int?
    ):this(
        if(uno==null) 0 else uno,
        if(dos == null) 0 else dos,
    )
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

