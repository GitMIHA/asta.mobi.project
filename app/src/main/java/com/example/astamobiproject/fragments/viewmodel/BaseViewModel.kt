package com.example.astamobiproject.fragments.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_profile.*

class BaseViewModel: ViewModel() {
    lateinit var personPhoto: Uri

    lateinit var personNumber: String
    lateinit var personName: String
    lateinit var personSecondName: String
    lateinit var personLocation: String
    lateinit var personEmail: String

    fun getNumber(): String{
        return personNumber
    }
    fun setNumber(personNumber: String) {
        this.personNumber = personNumber
    }

    fun getName(): String{
        return personName
    }
    fun setName(personName: String) {
        this.personName = personName
    }

    fun getSecondName(): String{
        return personSecondName
    }
    fun setSecondName(personSecondName: String) {
        this.personSecondName = personSecondName
    }

    fun getLocation(): String{
        return personLocation
    }
    fun setLocation(personLocation: String) {
        this.personLocation = personLocation
    }

    fun getEmail(): String{
        return personEmail
    }
    fun setEmail(personEmail: String) {
        this.personEmail = personEmail
    }


}