package com.example.astamobiproject.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.astamobiproject.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*


class FragmentProfile : Fragment() {

    var imageUri: Uri? = null

    override fun onStart() {
        super.onStart()

        val viewModeL = ViewModelProvider(this).get(ProfileViewModel::class.java)


        val numberUser = activity?.intent?.getStringExtra("numberUser").toString()
        textViewForNumber.text = numberUser
        val nameUser = activity?.intent?.getStringExtra("nameUser").toString()
        textViewForName.text = nameUser
        val surnameUser = activity?.intent?.getStringExtra("surnameUser").toString()
        textViewForSecondName.text = surnameUser
        val cityUser = activity?.intent?.getStringExtra("cityUser").toString()
        textViewForCity.text = cityUser
        val emailUser = activity?.intent?.getStringExtra("emailUser").toString()
        textViewForemailUser.text = emailUser


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddPhoto.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            imageUri = data?.data
            profile_image.setImageURI(imageUri)
        }

    }

}