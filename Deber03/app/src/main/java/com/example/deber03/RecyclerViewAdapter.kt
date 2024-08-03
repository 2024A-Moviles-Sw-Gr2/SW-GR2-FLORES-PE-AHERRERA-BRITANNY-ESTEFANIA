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
        val sectionName:TextView = view.findViewById(R.id.descripcion)
        val recyclerView:RecyclerView = view.findViewById(R.id.recyclerView2)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_horizontal,parent,false)
        return SectionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaPeliculas.size
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val (sectionName, imageList) = listaPeliculas[position]
        holder.sectionName.text = sectionName.toString()


        // Configurar el RecyclerView horizontal
        holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerView.adapter = HorizontalRecyclerViewAdapter(imageList)

    }

}