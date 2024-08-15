package com.example.deber03

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

//RecyclerView horizontal que muestra imágenes
class HorizontalRecyclerViewAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Referencia a la vista de imagen dentro del ViewHolder
        val productImage: ImageView = view.findViewById(R.id.imageView)
    }

    // Se llama cuando se necesita crear una nueva vista para un elemento del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // Infla el diseño de item 'pelicula' y lo convierte en un View
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.pelicula, parent, false)
        // Devuelve un nuevo ImageViewHolder con la vista inflada
        return ImageViewHolder(itemView)
    }

    // Se llama para enlazar los datos del modelo con la vista del ViewHolder
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        // Establece la imagen en el ImageView del ViewHolder usando el recurso de imagen correspondiente
        holder.productImage.setImageResource(imageList[position])
    }

    // Devuelve el número total de elementos en la lista
    override fun getItemCount(): Int = imageList.size
}




