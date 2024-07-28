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

class editarArtistaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_artista)

        val precioPromedio = findViewById<EditText>(R.id.edit_precio_prom)
        val idArtista = intent.getIntExtra("idArtista", -1)

        val btnActualizarArtista = findViewById<Button>(R.id.guardar_precio_artista)
        btnActualizarArtista.setOnClickListener {
            if (idArtista != -1){
                DataBase.tables!!.updateArtista(idArtista, precioPromedio.text.toString().toDouble())
            }
            irActividad(MainActivity::class.java)
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