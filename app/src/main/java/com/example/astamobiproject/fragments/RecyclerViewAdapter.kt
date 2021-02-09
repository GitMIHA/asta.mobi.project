package com.example.astamobiproject.fragments

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astamobiproject.R

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    var imageArray = arrayOf(R.drawable.appicon,R.drawable.appicon)

    private var modelArray = arrayOf("nike air jordan", "nike air max")
    private var priceArray = arrayOf("999", "444")
    private var ueArray = arrayOf("40", "43")
    private var lengthArray = arrayOf("29", "30")
    private var widthArray = arrayOf("23", "24")
    private var materialArray = arrayOf("Хлопок, текстиль", "Ткань, резина, піна")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  priceArray.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
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
            modelImageOne = itemView.findViewById(R.id.imageViewforModel)
            model = itemView.findViewById(R.id.textViewModelName)
            modelPrice = itemView.findViewById(R.id.textViewModelPrice)
            modelUE = itemView.findViewById(R.id.textViewForEU)
            modelLength = itemView.findViewById(R.id.textViewForLength)
            modelWidth = itemView.findViewById(R.id.textViewForWidth)
            modelMaterial = itemView.findViewById(R.id.textViewForMaterial)

        }



    }
}