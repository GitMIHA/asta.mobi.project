package com.example.astamobiproject.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astamobiproject.R
import com.example.astamobiproject.db.BaseItemDB
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class RVAdapterMyAds(options: FirebaseRecyclerOptions<BaseItemDB>) :
    FirebaseRecyclerAdapter<BaseItemDB, RVAdapterMyAds.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_row, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: BaseItemDB) {

        Glide.with(holder.modelImageOne!!.context).load(model.itemFirstBitmap).into(holder.modelImageOne!!)
        holder.model!!.text = model.itemModel
        holder.modelPrice!!.text = model.itemPrice
        holder.modelEU!!.text = model.itemEUSize
        holder.modelLength!!.text =model.itemLength
        holder.modelWidth!!.text = model.itemWidth
        holder.modelMaterial!!.text = model.itemMaterial
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var modelImageOne: ImageView? = null
        var model: TextView? = null
        var modelPrice: TextView? = null
        var modelEU: TextView? = null
        var modelLength: TextView? = null
        var modelWidth: TextView? = null
        var modelMaterial: TextView? = null

        init {
            modelImageOne = itemView.findViewById(R.id.imageViewForModel)
            model = itemView.findViewById(R.id.textViewModelName)
            modelPrice = itemView.findViewById(R.id.textViewModelPrice)
            modelEU = itemView.findViewById(R.id.textViewForEU)
            modelLength = itemView.findViewById(R.id.textViewForLength)
            modelWidth = itemView.findViewById(R.id.textViewForWidth)
            modelMaterial = itemView.findViewById(R.id.textViewForMaterial)
        }


    }

}
