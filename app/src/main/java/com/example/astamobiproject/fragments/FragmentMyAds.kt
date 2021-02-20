package com.example.astamobiproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astamobiproject.R
import com.example.astamobiproject.fragments.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_my_ads.*

class FragmentMyAds : Fragment() {

    private  var layoutManager: RecyclerView.LayoutManager? = null
    private  var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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

        adapter =
            RecyclerViewAdapter()
        recyclerViewMyAds.adapter = adapter

    }


}