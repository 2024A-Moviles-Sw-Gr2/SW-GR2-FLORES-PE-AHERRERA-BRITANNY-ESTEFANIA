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
import com.example.pinturasyartistasdeber2.Entidades.Pintura

class ArtistaActivity : AppCompatActivity() {
    private var arrayPinturas:ArrayList<Pintura> = arrayListOf();
    private var adaptador:ArrayAdapter<Pintura>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artista)

        DataBase.tables = SqliteHelper(this)
        val idArtista = intent.getIntExtra("idArtista", -1) // para sacar el id
        arrayPinturas = DataBase.tables!!.getPinturasPorArtista(idArtista)

        //Logica lista
        val listView = findViewById<ListView>(R.id.listpinturas)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arrayPinturas
        )
        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()// Para que se pueda actualizar
        registerForContextMenu(listView)

        /*BOTON CREAR*/
        val btnCraerPintura = findViewById<Button>(R.id.btnCrearPintura)
        btnCraerPintura.setOnClickListener { irActividad(CrearPintura::class.java, idArtista) }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /*********************************************************************************************/
    var posicionItemSeleccionado = -1
    /* Para las opciones de editar y eliminar */
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pintura,menu)
        //ObtenerID
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editarpin ->{
                irActividadEditarPintura(EditarPinturaActivity::class.java, arrayPinturas[posicionItemSeleccionado].id)
                return true
            }
            R.id.mi_eliminarpin ->{
                openDialogEliminar(arrayPinturas[posicionItemSeleccionado].id)
                return true
            }
            R.id.mi_ver_ubicacion ->{
                irActividadMapa(GGoogleMapsActivity::class.java, arrayPinturas[posicionItemSeleccionado])
                return true
            }
            else-> super.onContextItemSelected(item)

        }
    }

    private fun irActividadMapa(clase: Class<GGoogleMapsActivity>, pintura: Pintura) {
            val intent = Intent(this,clase)
            intent.apply {
                putExtra("latitud", pintura.latitud)
                putExtra("longitud", pintura.longitud)
            }

        startActivity(intent)
    }

    private fun irActividad(
        clase:Class<*>,
        id: Int
    ){
        val intent = Intent(this,clase)
        intent.apply { putExtra("idArtista", id) }
        startActivity(intent)
    }

    private fun openDialogEliminar(index: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar la pintura?")
        builder.setPositiveButton(
            "Eliminar"
        ){_,_ ->
            DataBase.tables!!.deletePintura(index)
            actualizarListaArtista()
        }
        builder.setNegativeButton("Cancelar",null)
        builder.create().show()
    }

    private fun actualizarListaArtista(){
        arrayPinturas.clear()
        arrayPinturas.addAll(DataBase.tables!!.getPinturasPorArtista(intent.getIntExtra("idArtista", -1)))
        adaptador!!.notifyDataSetChanged()
    }


    private fun irActividadEditarPintura(clase: Class<EditarPinturaActivity>, id: Int) {
        val intent = Intent(this,clase)
        if (id!=null){
            intent.apply {
                putExtra("idPintura",id)
            }
        }
        startActivity(intent)
    }
}