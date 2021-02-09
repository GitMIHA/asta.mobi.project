package com.example.astamobiproject.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astamobiproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_my_ads.*

public class HomePage: AppCompatActivity() {

    private  var layoutManager: RecyclerView.LayoutManager? = null
    private  var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navigation)

        val bottomNavigableView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navController = findNavController(R.id.idFragment)
        bottomNavigableView.setupWithNavController(navController)



    }

}