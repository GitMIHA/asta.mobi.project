package com.example.astamobiproject.fragments

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.astamobiproject.R
import com.example.astamobiproject.db.ItemDatabase
import com.example.astamobiproject.login.Login
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_create_ads.*


class FragmentCreateAds : Fragment() {

    var ITEMS_KAY = "MY_ITEMS"
    var database: DatabaseReference? = null

    var sPref: SharedPreferences? = null

    var imageUri: Uri? = null
    var imageUri_1: Uri? = null
    var imageUri_2: Uri? = null
    var imageUri_3: Uri? = null
    var imageUri_4: Uri? = null

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

        database = FirebaseDatabase.getInstance().getReference(ITEMS_KAY)

        buttonComeback_to_home.setOnClickListener {

        }

        buttonSaveItem.setOnClickListener {
            addItemToDB()
        }

        imageViewForPhoto_1.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

//        val newUser = NewUserDB(number, name, surname, city, email)
//        database?.push()?.setValue(newUser)

    }

    fun addItemToDB() {

        val uriImage = imageUri.toString()
        val euSize = editItemEUsize.text.toString()
        val itemLength = editItemLength.text.toString()
        val itemWidth = editItemWidth.text.toString()
        val itemModel = editItemModel.text.toString()
        val itemMaterial = editItemMaterial.text.toString()
        val itemDescription = editItemDescription.text.toString()
        val itemPrice = editIremPrice.text.toString()


        if(!TextUtils.isEmpty(uriImage) && !TextUtils.isEmpty(euSize) &&
            !TextUtils.isEmpty(itemLength) && !TextUtils.isEmpty(itemWidth) &&
            !TextUtils.isEmpty(itemModel) && !TextUtils.isEmpty(itemMaterial) &&
            !TextUtils.isEmpty(itemDescription) && !TextUtils.isEmpty(itemPrice))
        {
            val addOrder = ItemDatabase(uriImage, euSize, itemLength, itemWidth, itemModel, itemMaterial, itemDescription, itemPrice)
            database?.push()?.setValue(addOrder)
            val intent = Intent(activity, HomePage::class.java)
            startActivity(intent)
            Toast.makeText(activity, "Успішно збережено", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(activity, "Деяке поле пусте", Toast.LENGTH_SHORT).show()
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            imageUri = data?.data
//           sPref = activity?.getPreferences(Context.MODE_PRIVATE)
//            val editor = sPref?.edit()
//            editor?.putString("imageProfileUri", imageUri.toString())
//           editor?.apply()
//           Glide.with(this).load(sPref?.getString("imageProfileUri","")).into(imageViewForPhoto_1)
            imageViewForPhoto_1.setImageURI(imageUri)
        }

    }
}