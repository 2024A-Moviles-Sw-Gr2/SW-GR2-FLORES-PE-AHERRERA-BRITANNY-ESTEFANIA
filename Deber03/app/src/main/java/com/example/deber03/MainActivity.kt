package com.example.deber03


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarVista()
    }

    private fun inicializarVista() {
        val listaPeliculas = listOf(
            "Peliculas - Series que pensamos en ti" to listOf(R.drawable.pelicula2,R.drawable.pelicula3,R.drawable.pelicula4,R.drawable.pelicula5),
            "Prime - Peliculas mas populares de Prime" to listOf(
                R.drawable.pelicula11,R.drawable.pelicula6,R.drawable.pelicula7,R.drawable.pelicula8
            ),
            "Prime - Peliculas para compartir en familia" to listOf(
                R.drawable.pelicula9,R.drawable.pelicula10,R.drawable.pelicula12,R.drawable.pelicula21
            ),
            "Prime - Las series mas vistas de Prime" to listOf(
                R.drawable.pelicula14,R.drawable.pelicula15,R.drawable.pelicula16,R.drawable.pelicula17,R.drawable.pelicula18
            ),
            "Peliculas - Series que pensamos en ti" to listOf(R.drawable.pelicula19,R.drawable.pelicula20,R.drawable.pelicula,R.drawable.pelicula21),
            )
        val recyclerView:RecyclerView = findViewById(R.id.recycler_view) // vertical
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(this,listaPeliculas)
    }
}