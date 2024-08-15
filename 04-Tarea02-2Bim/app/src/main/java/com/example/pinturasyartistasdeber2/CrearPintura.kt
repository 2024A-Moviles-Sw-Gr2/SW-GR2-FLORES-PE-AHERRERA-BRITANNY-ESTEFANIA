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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CrearPintura : AppCompatActivity() {
    private lateinit var fecha: EditText;
    private var calendar = Calendar.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_pintura)
        fecha = findViewById(R.id.fecha_creacion)
        fecha.setOnClickListener { abrirDialogo() }

        val nombrePintura = findViewById<EditText>(R.id.nomb_pintura)
        val fueVendida = findViewById<CheckBox>(R.id.fue_vendida)
        val precio = findViewById<EditText>(R.id.precio_promediopin)
        val idArtista = intent.getIntExtra("idArtista", -1)

        val btnGuardarPintura = findViewById<Button>(R.id.btn_guardar_pintura)
        btnGuardarPintura.setOnClickListener {
            val coordendas = findViewById<EditText>(R.id.text_latitud).text.toString().split(",")
            val latitud = coordendas[0]
            val longitud = coordendas[1]
            DataBase.tables!!.createPintura(
                nombrePintura.text.toString(),
                idArtista,
                fecha.text.toString(),
                fueVendida.text.toString().toBoolean(),
                precio.text.toString().toDouble(),
                latitud.toDouble(),
                longitud.toDouble()
            )
            irActividad(ArtistaActivity::class.java)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun irActividad(
        activityClass: Class<*>
    ) {
        val intent2 = Intent(this, activityClass)
        intent2.apply { putExtra("idArtista", intent.getIntExtra("idArtista", -1)) }
        startActivity(intent2)
    }
    private fun abrirDialogo() {
        val dialog = DatePickerDialog.OnDateSetListener{ view, year, month, day ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,day)
            actualizarEditText()
        }
        DatePickerDialog(this,dialog,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(
            Calendar.DAY_OF_MONTH)).show()
    }

    fun actualizarEditText(){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        fecha.setText(dateFormat.format(calendar.time))
    }
}