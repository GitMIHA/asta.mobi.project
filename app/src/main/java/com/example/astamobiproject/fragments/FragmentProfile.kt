package com.example.astamobiproject.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.astamobiproject.R
import com.example.astamobiproject.fragments.viewmodel.BaseViewModel
import com.example.astamobiproject.login.Login
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*


class FragmentProfile : Fragment() {

    var imageUri: Uri? = null

    var numberUser: String? = null
    var nameUser: String? = null
    var surnameUser: String? = null
    var cityUser: String? = null
    var emailUser: String? = null

    var sPref: SharedPreferences? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        googleInfo()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()




        val viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)



        nameUser = activity?.intent?.getStringExtra("nameUser").toString()
//        textViewForName.text = nameUser
        surnameUser = activity?.intent?.getStringExtra("surnameUser").toString()
//        textViewForSecondName.text = surnameUser
        numberUser = activity?.intent?.getStringExtra("numberUser").toString()
//        textViewForNumber.text = numberUser
        cityUser = activity?.intent?.getStringExtra("cityUser").toString()
//        textViewForCity.text = cityUser
        emailUser = activity?.intent?.getStringExtra("emailUser").toString()
//        textViewForemailUser.text = emailUser

//        viewModel.setName(nameUser!!)
//        textViewForName.text = viewModel.getName()
//        viewModel.setSecondName(surnameUser!!)
//        textViewForSecondName.text = viewModel.getSecondName()

        sPref = activity?.getPreferences(Context.MODE_PRIVATE)
        Glide.with(this).load(sPref?.getString("imageProfileUri","")).into(profile_image)

//        loadUserInfo()

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

        buttonExitFromStore.setOnClickListener{
            mAuth.signOut()

            LoginManager.getInstance().logOut();

            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
        }


    }
    fun facebookInfo(){

    }
    fun googleInfo(){
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        if(currentUser!=null){
        Glide.with(this).load(currentUser.photoUrl.toString()).into(profile_image)

//        textViewForName.text = currentUser?.displayName
            val name = currentUser.displayName
            val fullName = name!!.split(" ")
            val nameUser = fullName[0]
            val lastNameUser = fullName[1]
            textViewForName.text = nameUser
            textViewForSecondName.text = lastNameUser

//        textViewForSecondName.text = surnameUser

//        textViewForNumber.text = currentUser.phoneNumber
            textViewForNumber.text = "Number"
//        textViewForCity.text = currentUser

            textViewForemailUser.text = currentUser.email
        }

    }

    private fun loadUserInfo() {
        sPref = this.activity?.getPreferences(Context.MODE_PRIVATE)

        Glide.with(this).load(sPref?.getString("imageProfileUri","")).into(profile_image)

        textViewForName.text = sPref?.getString("nameUserF", "Name")
        textViewForSecondName.text = sPref?.getString("surnameUserF", "Second Name")
        textViewForNumber.text = sPref?.getString("numberUserF", "Number")
        textViewForCity.text = sPref?.getString("cityUserF", "City")
        textViewForemailUser.text = sPref?.getString("emailUserF", "Email User")

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(activity, "permssion not", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            imageUri = data?.data

            sPref = activity?.getPreferences(Context.MODE_PRIVATE)
            val editor = sPref?.edit()
            editor?.putString("imageProfileUri", imageUri.toString())

//            editor?.putString("numberUserF", numberUser)
//            editor?.putString("nameUserF", nameUser)
//            editor?.putString("surnameUserF", surnameUser)
//            editor?.putString("cityUserF", cityUser)
//            editor?.putString("emailUserF", emailUser)

            editor?.apply()

            Glide.with(this).load(sPref?.getString("imageProfileUri","")).into(profile_image)


            loadUserInfo()

//            profile_image.setImageURI(imageUri)
        }

    }

}