package com.example.astamobiproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astamobiproject.R
import com.example.astamobiproject.db.BaseItemDB
import com.example.astamobiproject.fragments.adapters.RVAdapterMyAds
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.card_row.view.*
import kotlinx.android.synthetic.main.fragment_create_ads.view.*
import kotlinx.android.synthetic.main.fragment_my_ads.*


class FragmentMyAds : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapterMyAds: RecyclerView.Adapter<RVAdapterMyAds.ViewHolder>

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_ads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(activity)
        recyclerViewMyAds.layoutManager = layoutManager

        val options: FirebaseRecyclerOptions<BaseItemDB> = FirebaseRecyclerOptions.Builder<BaseItemDB>()
                .setQuery(FirebaseDatabase.getInstance().reference.child("MY_ITEMS"),
                    BaseItemDB::class.java)
                .build()

        adapterMyAds = RVAdapterMyAds(options)
        recyclerViewMyAds!!.adapter = adapterMyAds

    }

}





