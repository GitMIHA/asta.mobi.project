package com.example.astamobiproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astamobiproject.R
import com.example.astamobiproject.fragments.adapters.RecyclerViewAdapterHome
import kotlinx.android.synthetic.main.card_for_home.*
import kotlinx.android.synthetic.main.fragment_home.*


class FragmentHome : Fragment() {

    private  var layoutManager: RecyclerView.LayoutManager? = null
    private  var adapter: RecyclerView.Adapter<RecyclerViewAdapterHome.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(activity)
        recyclerViewHome.layoutManager = layoutManager

        adapter = RecyclerViewAdapterHome()
        recyclerViewHome.adapter = adapter

    }



}