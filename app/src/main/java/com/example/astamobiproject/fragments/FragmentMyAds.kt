package com.example.astamobiproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astamobiproject.R
import com.example.astamobiproject.db.BaseItemDB
import com.example.astamobiproject.fragments.adapters.RVAdapterMyAds
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FragmentMyAds : Fragment() {

//    private lateinit var layoutManager: RecyclerView.LayoutManager
//    private lateinit var adapterMyAds: RecyclerView.Adapter<RVAdapterMyAds.ViewHolder>

    private  var recyclerView: RecyclerView? = null
    private  var adapterMyAds: RecyclerView.Adapter<RVAdapterMyAds.ViewHolder>? = null

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

//        layoutManager = LinearLayoutManager(activity)
//        recyclerViewMyAds.layoutManager = layoutManager
        recyclerView = activity?.findViewById(R.id.recyclerViewMyAds)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        val options: FirebaseRecyclerOptions<BaseItemDB> = FirebaseRecyclerOptions.Builder<BaseItemDB>()
                .setQuery(FirebaseDatabase.getInstance().reference.child("MY_ITEMS"),
                    BaseItemDB::class.java)
                .build()

        adapterMyAds = RVAdapterMyAds(options)
        recyclerView!!.adapter = adapterMyAds


    }

    override fun onStart() {
        super.onStart()
//        adapterMyAds.startListening()
    }
    override fun onStop() {
        super.onStop()
//        adapterMyAds.startListening()
    }


}





