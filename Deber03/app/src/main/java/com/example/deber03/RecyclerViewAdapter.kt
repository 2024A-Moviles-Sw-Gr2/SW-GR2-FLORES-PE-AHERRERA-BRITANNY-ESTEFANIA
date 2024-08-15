package com.example.deber03

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context,private val listaPeliculas: List<Pair<String, List<Int>>>) : RecyclerView.Adapter<RecyclerViewAdapter.SectionViewHolder>() {


    // vertical - recycler horizontal.xml
    inner class SectionViewHolder(view: View):RecyclerView.ViewHolder(view){
        // Referencia a un TextView para mostrar el nombre de la sección
        val sectionName: TextView = view.findViewById(R.id.descripcion)
        // Referencia a un RecyclerView para mostrar una lista horizontal de imágenes
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView2)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_horizontal,parent,false)
        return SectionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaPeliculas.size
    }

    // Se llama para enlazar los datos del modelo con la vista del ViewHolder
    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        // Obtiene el nombre de la sección y la lista de imágenes para la posición actual
        val (sectionName, imageList) = listaPeliculas[position]
        // Establece el texto del TextView con el nombre de la sección
        holder.sectionName.text = sectionName.toString()

        // Configura el RecyclerView horizontal dentro del ViewHolder
        holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // Establece el adaptador del RecyclerView con la lista de imágenes
        holder.recyclerView.adapter = HorizontalRecyclerViewAdapter(imageList)
    }
}