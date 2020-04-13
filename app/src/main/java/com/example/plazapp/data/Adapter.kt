package com.example.plazapp.data

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plazapp.R
import kotlinx.android.synthetic.main.content_item.view.*

class Adapter (var list:ArrayList<Items>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindItem(data:Items){
            itemView.tvtexto.text = data.nombre
            itemView.tvDescripcion.text = data.descripcion
            //Glide.with(itemView.context).load(data.thumbnail).into(itemView.thumbnail)
            itemView.setOnClickListener{
                /*val intent = Intent( it.context, PlatilloActivity::class.java)
                intent.putExtra(tag, data.id.toString())
                it.context.startActivity(intent)*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_item,parent,false)
        return ViewHolder (view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }
}