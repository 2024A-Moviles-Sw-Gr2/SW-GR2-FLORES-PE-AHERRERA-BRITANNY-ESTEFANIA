package com.example.pinturasyartistasdeber2

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pinturasyartistasdeber2.Controlador.DataBase
import com.example.pinturasyartistasdeber2.Entidades.Artista
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CrearArtistaActivity : AppCompatActivity() {

    private lateinit var fecha: EditText // Campo de texto para la fecha de nacimiento
    var calendar = Calendar.getInstance() // Instancia del calendario para gestionar fechas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_artista) // Establece el layout de la actividad

        // Inicializa el campo de texto para la fecha de nacimiento
        fecha = findViewById(R.id.fecha_nacimiento)
        fecha.setOnClickListener { abrirDialogo() } // Abre el diálogo de selección de fecha al hacer clic

        // Inicializa los campos de texto y checkbox de la vista
        val nombre = findViewById<EditText>(R.id.nombre_artista)
        val estaVivo = findViewById<CheckBox>(R.id.esta_vivo)
        val numObras = findViewById<EditText>(R.id.num_obras)
        val precioPromedio = findViewById<EditText>(R.id.precio_prom)

        // Inicializa el botón de guardar artista y establece un listener de clic
        val btnGuardarArtista = findViewById<Button>(R.id.btn_guardar_artista)
        btnGuardarArtista.setOnClickListener {
            // Llama al método createArtista de la base de datos para guardar el nuevo artista
            DataBase.tables!!.createArtista(
                nombre.text.toString(),
                fecha.text.toString(),
                estaVivo.isChecked,
                numObras.text.toString().toInt(),
                precioPromedio.text.toString().toDouble()
            )
            irActividad(MainActivity::class.java)
        }

        // Ajusta el padding de la vista principal para respetar las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Método para navegar a otra actividad
    private fun irActividad(activityClass: Class<*>) {
        val intent = Intent(this, activityClass) // Crea un intent para la actividad especificada
        startActivity(intent) // Inicia la nueva actividad
    }

    // Método para abrir el diálogo de selección de fecha
    private fun abrirDialogo() {
        // Configura el listener para manejar la selección de fecha
        val dialog = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calendar.set(Calendar.YEAR, year) // Establece el año seleccionado
            calendar.set(Calendar.MONTH, month) // Establece el mes seleccionado
            calendar.set(Calendar.DAY_OF_MONTH, day) // Establece el día seleccionado
            actualizarEditText() // Actualiza el campo de texto con la fecha seleccionada
        }
        // Muestra el diálogo de selección de fecha
        DatePickerDialog(
            this, dialog, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    // Método para actualizar el campo de texto de la fecha con la fecha seleccionada
    fun actualizarEditText() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US) // Define el formato de la fecha
        fecha.setText(dateFormat.format(calendar.time)) // Establece el texto del campo de fecha
    }
}
