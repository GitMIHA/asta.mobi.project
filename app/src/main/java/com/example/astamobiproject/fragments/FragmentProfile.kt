package com.example.astamobiproject.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.astamobiproject.R
import kotlinx.android.synthetic.main.fragment_profile.*
import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream


class FragmentProfile : Fragment() {

    var imageUri: Uri? = null

    var numberUser: String? = null
    var nameUser: String? = null
    var surnameUser: String? = null
    var cityUser: String? = null
    var emailUser: String? = null




    override fun onStart() {
        super.onStart()

//        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        nameUser = activity?.intent?.getStringExtra("nameUser").toString()
        textViewForName.text = nameUser

        surnameUser = activity?.intent?.getStringExtra("surnameUser").toString()
        textViewForSecondName.text = surnameUser

        numberUser = activity?.intent?.getStringExtra("numberUser").toString()
        textViewForNumber.text = numberUser

        cityUser = activity?.intent?.getStringExtra("cityUser").toString()
        textViewForCity.text = cityUser

        emailUser = activity?.intent?.getStringExtra("emailUser").toString()
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

        val animationDrawable = layoutProfile.background as AnimationDrawable
        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(1500)
            start()
        }

        buttonAddPhoto.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }
    override fun onRequestPermissionsResult( requestCode: Int,permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{
                Toast.makeText(activity,"permssion not",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            imageUri = data?.data
            profile_image.setImageURI(imageUri)
        }

    }

}