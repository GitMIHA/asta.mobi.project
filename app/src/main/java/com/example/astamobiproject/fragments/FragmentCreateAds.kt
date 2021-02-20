package com.example.astamobiproject.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.astamobiproject.R
import com.example.astamobiproject.db.NewUserDB
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_create_ads.*
import kotlinx.android.synthetic.main.fragment_profile.*


class FragmentCreateAds : Fragment() {

    var User_Kay = "MY_ITEMS"
    var database: DatabaseReference? = null


    var sPref: SharedPreferences? = null

    var imageUri: Uri? = null

    var itemBitmap: Bitmap? = null

    var itemEUSize: String? = null
    var itemLength: String? = null
    var itemWidth: String? = null

    var itemModel: String? = null
    var itemMaterial: String? = null
    var itemDescription: String? = null
    var itemPrice: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_ads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonComeback_to_home.setOnClickListener{

        }

        buttonSaveItem.setOnClickListener{}

        imageViewForPhoto_1.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

//        val newUser = NewUserDB(number, name, surname, city, email)
//        database?.push()?.setValue(newUser)

    }
    fun workDB(){
        database = FirebaseDatabase.getInstance().getReference(User_Kay)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            imageUri = data?.data
//            sPref = activity?.getPreferences(Context.MODE_PRIVATE)
//            val editor = sPref?.edit()
//            editor?.putString("imageProfileUri", imageUri.toString())
//            editor?.apply()
//            Glide.with(this).load(sPref?.getString("imageProfileUri","")).into(imageViewForPhoto_1)
            imageViewForPhoto_1.setImageURI(imageUri)
        }

    }
}