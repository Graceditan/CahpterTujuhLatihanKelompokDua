package com.example.cahptertujuhlatihankelompokdua.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cahptertujuhlatihankelompokdua.R
import com.example.cahptertujuhlatihankelompokdua.model.GetMenuItem
import kotlinx.android.synthetic.main.item_makanan.view.*

class AdapterHome (private val onclick : (GetMenuItem)->Unit) : RecyclerView.Adapter<AdapterHome.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    var datafilm : List<GetMenuItem>? = null


    fun setDataFilm(film  : List<GetMenuItem>){
        this.datafilm = film
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_makanan, parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(datafilm!![position].gambar).into(holder.itemView.gambar
        )

        holder.itemView.text1.text = datafilm!![position].namaMakanan
        holder.itemView.text2.text = datafilm!![position].harga
//        holder.itemView.text3.text = datafilm!![position].releaseDate

        holder.itemView.card.setOnClickListener {
            onclick(datafilm!![position])

        }
    }

    override fun getItemCount(): Int {
        if (datafilm == null){
            return 0
        }else{
            return datafilm!!.size
        }
    }

}