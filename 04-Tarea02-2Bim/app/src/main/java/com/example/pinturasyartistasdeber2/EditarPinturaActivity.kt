package com.example.pinturasyartistasdeber2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pinturasyartistasdeber2.Controlador.DataBase

class EditarPinturaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_pintura)
        val precioObra = findViewById<EditText>(R.id.precio_nuevo_pintura)
        val idPintura = intent.getIntExtra("idPintura", -1)

        val btnEditarPintura = findViewById<Button>(R.id.guardar_nuevo_preciopin)
        btnEditarPintura.setOnClickListener {
            if (idPintura != -1){
                DataBase.tables!!.updatePintura(idPintura, precioObra.text.toString().toDouble())
            }
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
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}