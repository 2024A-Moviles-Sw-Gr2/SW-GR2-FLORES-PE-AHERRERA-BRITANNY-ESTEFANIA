package com.example.pinturasyartistasdeber2

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pinturasyartistasdeber2.Controlador.DataBase
import com.example.pinturasyartistasdeber2.Controlador.SqliteHelper
import com.example.pinturasyartistasdeber2.Entidades.Artista

class MainActivity : AppCompatActivity() {

    // Lista para almacenar los artistas
    private var arrayArtista: ArrayList<Artista> = arrayListOf()
    // Adaptador para mostrar los artistas en una lista
    private var adaptador: ArrayAdapter<Artista>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Establece el layout de la actividad
        setContentView(R.layout.activity_main)
        // Inicializa la base de datos
        DataBase.tables = SqliteHelper(this)
        // Obtiene todos los artistas de la base de datos
        arrayArtista = DataBase.tables!!.getAllArtistas()

        // Configura la vista de la lista
        val listView = findViewById<ListView>(R.id.artistas)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arrayArtista
        )
        listView.adapter = adaptador

        // Actualiza el adaptador para mostrar los datos más recientes
        adaptador!!.notifyDataSetChanged()
        // Registra la lista para el menú de contexto
        registerForContextMenu(listView)

        // Configura el botón para crear un nuevo artista
        val btnCrearArtista = findViewById<Button>(R.id.btn_crear_artista)
        btnCrearArtista.setOnClickListener {
            // Llama al método para abrir la actividad de creación de artista
            irActividadVerArtista(CrearArtistaActivity::class.java)
        }

        // Ajusta el padding de la vista principal para que respete las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Variable para almacenar la posición del item seleccionado en el menú de contexto
    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Infla el menú de contexto desde el archivo de recursos
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_artista, menu)
        // Obtiene la información del menú de contexto
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        // Almacena la posición del item seleccionado
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // Maneja las opciones seleccionadas en el menú de contexto
        return when (item.itemId) {
            R.id.mi_ver -> {
                // Abre la actividad para ver detalles del artista
                irActividadVerArtista(ArtistaActivity::class.java, arrayArtista[posicionItemSeleccionado].id)
                true
            }
            R.id.mi_editar -> {
                // Abre la actividad para editar el artista
                irActividadEditarArtista(editarArtistaActivity::class.java, arrayArtista[posicionItemSeleccionado].id)
                // Actualiza la lista de artistas
                actualizarListaArtista()
                true
            }
            R.id.mi_eliminar -> {
                // Muestra un diálogo para confirmar la eliminación del artista
                openDialogEliminar(arrayArtista[posicionItemSeleccionado].id)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Actualiza la lista de artistas obteniendo los datos más recientes
    private fun actualizarListaArtista() {
        arrayArtista.clear()
        arrayArtista.addAll(DataBase.tables!!.getAllArtistas())
        adaptador!!.notifyDataSetChanged()
    }

    // Muestra un diálogo para confirmar la eliminación del artista
    private fun openDialogEliminar(index: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar el Artista?")
        builder.setPositiveButton("Eliminar") { _, _ ->
            // Elimina el artista de la base de datos y actualiza la lista
            DataBase.tables!!.deleteArtista(index)
            actualizarListaArtista()
        }
        builder.setNegativeButton("Cancelar", null)
        builder.create().show()
    }

    // Abre una actividad con el id del artista pasado como extra
    private fun irActividadVerArtista(clase: Class<ArtistaActivity>, id: Int) {
        val intent = Intent(this, clase)
        if (id != null) {
            intent.apply {
                putExtra("idArtista", id)
            }
        }
        startActivity(intent)
    }

    // Abre una actividad de edición de artista con el id pasado como extra
    private fun irActividadEditarArtista(clase: Class<editarArtistaActivity>, id: Int) {
        val intent = Intent(this, clase)
        if (id != null) {
            intent.apply {
                putExtra("idArtista", id)
            }
        }
        startActivity(intent)
    }

    // Abre una actividad sin pasar el id del artista
    private fun irActividadVerArtista(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
