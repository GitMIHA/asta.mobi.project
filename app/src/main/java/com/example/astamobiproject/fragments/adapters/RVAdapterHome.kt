package com.example.astamobiproject.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astamobiproject.R


class RVAdapterHome: RecyclerView.Adapter<RVAdapterHome.ViewHolder>() {

    var imageArray = arrayOf(R.drawable.appicon)
    private var modelArray = arrayOf("puma alteration PN-1")
    private var priceArray = arrayOf("999$")
    private var ueArray = arrayOf("42")
    private var lengthArray = arrayOf("29")
    private var widthArray = arrayOf("23")
    private var materialArray =arrayOf("Хлопок")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVAdapterHome.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_for_home, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return priceArray.size
    }

    override fun onBindViewHolder(holder: RVAdapterHome.ViewHolder, position: Int) {
        holder.modelImageOne!!.setImageResource(imageArray[position])
        holder.model!!.text = modelArray[position]
        holder.modelPrice!!.text = priceArray[position]
        holder.modelUE!!.text = ueArray[position]
        holder.modelLength!!.text = lengthArray[position]
        holder.modelWidth!!.text = widthArray[position]
        holder.modelMaterial!!.text = materialArray[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var modelImageOne: ImageView? = null
        var model: TextView? = null
        var modelPrice: TextView? = null
        var modelUE: TextView? = null
        var modelLength: TextView? = null
        var modelWidth: TextView? = null
        var modelMaterial: TextView? = null

        init {
            modelImageOne = itemView.findViewById(R.id.imageViewForModelHome)
            model = itemView.findViewById(R.id.textViewModelNameHome)
            modelPrice = itemView.findViewById(R.id.textViewModelPriceHome)
            modelUE = itemView.findViewById(R.id.textViewForEUHome)
            modelLength = itemView.findViewById(R.id.textViewForLengthHome)
            modelWidth = itemView.findViewById(R.id.textViewForWidthHome)
            modelMaterial = itemView.findViewById(R.id.textViewForMaterialHome)
        }

    }

}